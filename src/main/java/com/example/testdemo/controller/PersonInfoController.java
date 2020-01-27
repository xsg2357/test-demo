package com.example.testdemo.controller;

import com.example.testdemo.domain.PersonInfo;
import com.example.testdemo.domain.ResultBodyData;
import com.example.testdemo.domain.TokenInfo;
import com.example.testdemo.exception.CustomException;
import com.example.testdemo.exception.ErrorInfo;
import com.example.testdemo.repo.TokenRepository;
import com.example.testdemo.repo.UserRepository;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "个人信息")
@RequestMapping("/user")
@RestController
public class PersonInfoController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @ApiOperation(value="个人信息", notes="获取个人信息")
    @GetMapping("/userInfo")
    public ResultBodyData<PersonInfo> personInfo(HttpServletRequest request){
        //实时判断UserId
        String token = request.getHeader("token");
        List<TokenInfo> tokenInfoByToken = tokenRepository.findTokenInfoByToken(token);
        //判断tokenInfoByToken
        if (tokenInfoByToken == null || tokenInfoByToken.size()==0){
            throw  new CustomException(ErrorInfo.TOKEN_SESSION_ERROR);
        }
        int userId = tokenInfoByToken.get(0).getUserId();
        if (userId == 0){
            throw  new CustomException(ErrorInfo.USER_NOT_EXIST);
        }
      PersonInfo personInfo = userRepository.findPersonInfoById(userId);
        if (personInfo == null){
            throw  new CustomException(ErrorInfo.USER_ERROR);
        }

        return  new ResultBodyData<>(0,"ok",personInfo);
    }

    @ApiOperation(value="修改密码", notes="修改个人密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPwd",paramType = "query",value = "旧密码", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "newPwd", paramType = "query",value = "新密码", required = true ,dataType = "string"),
    })
    @GetMapping("/alterLoginPwd")
    public ResultBodyData<String> alterLoginPwd(HttpServletRequest request,@Param("oldPwd") String oldPwd, @Param("newPwd") String newPwd){
        if (StringUtil.isNullOrEmpty(oldPwd)) {
            throw new CustomException(ErrorInfo.PWD_NOT_EXIST);
        }
        if (StringUtil.isNullOrEmpty(newPwd)) {
            throw new CustomException(ErrorInfo.PWD_NEW_NOT_EXIST);
        }
        //忘记密码
        String token = request.getHeader("token");
        List<TokenInfo> tokenInfoByToken = tokenRepository.findTokenInfoByToken(token);
        //判断tokenInfoByToken
        if (tokenInfoByToken == null || tokenInfoByToken.size()==0){
            throw  new CustomException(ErrorInfo.TOKEN_SESSION_ERROR);
        }
        int userId = tokenInfoByToken.get(0).getUserId();
        if (userId == 0){
            throw  new CustomException(ErrorInfo.USER_NOT_EXIST);
        }
        PersonInfo personInfo = userRepository.findPersonInfoById(userId);
        if (personInfo == null){
            throw  new CustomException(ErrorInfo.USER_ERROR);
        }
        if (!oldPwd.equals(personInfo.getPwd())){
            throw  new CustomException(ErrorInfo.PWD_ERROR_EXIST);
        }

        personInfo.setPwd(newPwd);
        userRepository.save(personInfo);
        return  new ResultBodyData<>(0,"ok","");
    }

}
