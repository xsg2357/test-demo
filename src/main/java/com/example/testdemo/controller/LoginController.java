package com.example.testdemo.controller;

import com.example.testdemo.constant.CommonConstant;
import com.example.testdemo.domain.PersonInfo;
import com.example.testdemo.domain.ResultBodyData;
import com.example.testdemo.domain.TokenInfo;
import com.example.testdemo.exception.CustomException;
import com.example.testdemo.exception.ErrorInfo;
import com.example.testdemo.repo.TokenRepository;
import com.example.testdemo.repo.UserRepository;
import com.example.testdemo.util.WebUtil;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Api(description = "登录接口")
//header-->请求参数的获取数的获取：@RequestHeader(代码中接收注解)
//    query-->请求参数的获取：@RequestParam(代码中接收注解)
//    path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
//    body-->请求参数的获取：@RequestBody(代码中接收注解)
//    form（不常用）
@RestController
public class LoginController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;


    @ApiOperation(value="注册接口", notes="用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", paramType = "query", value = "电话号码", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "pwd",paramType = "query", value = "密码", required = true ,dataType = "string"),
    })
    @PostMapping("/register")
    public ResultBodyData<String> register(@Param("phone") String phone, @Param("pwd") String pwd) {

        if (StringUtil.isNullOrEmpty(phone)) {
            throw new CustomException(ErrorInfo.PHONE_NOT_EXIST);
        }
        if (!WebUtil.isMobile(phone)) {
            throw new CustomException(ErrorInfo.PHONE_NOT_ERROR);
        }
        if (StringUtil.isNullOrEmpty(pwd)) {
            throw new CustomException(ErrorInfo.PWD_NOT_EXIST);
        }

        List<PersonInfo> personInfoByPhone = userRepository.findPersonInfoByPhone(phone);

        if (personInfoByPhone != null && personInfoByPhone.size() > 0) {
            throw new CustomException(ErrorInfo.USER_EXIST);
        }

        PersonInfo personInfo = new PersonInfo();
        personInfo.setPhone(phone);
        personInfo.setPwd(pwd);
        userRepository.save(personInfo);

        return new ResultBodyData<>(0, "OK", "");
    }

    @ApiOperation(value="登录接口", notes="用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", paramType = "query",value = "电话号码", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "pwd",paramType = "query", value = "密码", required = true ,dataType = "string"),
    })
    @PostMapping("/login")
    public ResultBodyData<TokenInfo> login(HttpServletRequest request, @Param("phone") String phone, @Param("pwd") String pwd) {

        //手机号码不存在
        if (StringUtil.isNullOrEmpty(phone)) {
            throw new CustomException(ErrorInfo.PHONE_NOT_EXIST);
        }

        //手机号码错误
        if (!WebUtil.isMobile(phone)) {
            throw new CustomException(ErrorInfo.PHONE_NOT_ERROR);
        }

        List<PersonInfo> personInfoByPhone = userRepository.findPersonInfoByPhone(phone);
        if (personInfoByPhone == null || personInfoByPhone.size() == 0) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }
        PersonInfo personInfo1 = personInfoByPhone.get(0);
        if (personInfo1 == null) {
            throw new CustomException(ErrorInfo.USER_NOT_EXIST);
        }
        if (StringUtil.isNullOrEmpty(pwd)) {
            throw new CustomException(ErrorInfo.PWD_NOT_EXIST);
        }

        if (!personInfo1.getPhone().equals(phone) || !personInfo1.getPwd().equals(pwd)) {
            throw new CustomException(ErrorInfo.USER_PWD_NOT_EXIST);

        }

        List<TokenInfo> tokenInfosByUserId = tokenRepository.findTokenInfoByUserId(personInfo1.getId());
        TokenInfo tokenInfo;
        if (tokenInfosByUserId != null && tokenInfosByUserId.size() > 0) {
            tokenInfo = tokenInfosByUserId.get(0);
        } else {
            tokenInfo = new TokenInfo();
        }


        String newToken = UUID.randomUUID().toString();
        tokenInfo.setToken(newToken);
        long time = new Date().getTime();
        tokenInfo.setUpdateTime(String.valueOf(time));
        tokenInfo.setArriveTime(String.valueOf(time + CommonConstant.TOKEN_SET_TIME));
        tokenInfo.setSetTime(CommonConstant.TOKEN_SET_TIME);
        tokenInfo.setIsSingleClient(0);
        tokenInfo.setIsClient(0);
        tokenInfo.setUserId(personInfo1.getId());


        tokenRepository.save(tokenInfo);

        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute("sessionLogin", tokenInfo);
        }

        return new ResultBodyData<>(0, "OK", tokenInfo);
    }

    @ApiOperation(value="退出登录接口", notes="用户退出登陆")
    @PostMapping("/logout")
    public ResultBodyData<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();//获取当前session
        if (session != null) {
            TokenInfo tokenInfo = (TokenInfo) session.getAttribute("sessionLogin");//从当前session中获取用户信息
            String token = request.getHeader("token");
            if (tokenInfo==null || StringUtil.isNullOrEmpty(tokenInfo.getToken()) || StringUtil.isNullOrEmpty(token)) {
                throw new CustomException(ErrorInfo.TOKEN_SESSION_ERROR);
            }
            if (!token.equals(tokenInfo.getToken())) {
                throw new CustomException(ErrorInfo.TOKEN_SESSION_ERROR);
            }
            session.invalidate();//关闭session
            tokenInfo.setToken("");
            tokenInfo.setIsClient(1);
            tokenRepository.save(tokenInfo);
            try {
                request.logout();
            } catch (ServletException e) {
                throw new CustomException(1404, e.getMessage());
            }
        }else{
            throw new CustomException(ErrorInfo.TOKEN_SESSION_ERROR);
        }

        return new ResultBodyData<>(0, "OK", "");

    }


}
