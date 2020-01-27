package com.example.testdemo.controller;

import com.example.testdemo.domain.AddressInfo;
import com.example.testdemo.domain.PageEntity;
import com.example.testdemo.domain.ResultBodyData;
import com.example.testdemo.domain.TokenInfo;
import com.example.testdemo.exception.CustomException;
import com.example.testdemo.exception.ErrorInfo;
import com.example.testdemo.repo.TokenRepository;
import com.example.testdemo.service.AddressService;
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

@Api(description = "地址信息")
@RequestMapping("/address")
@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    TokenRepository tokenRepository;

    @ApiOperation(value="地址列表", notes="获取地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query",value = "分页页数", required = true ,dataType = "Integer"),
            @ApiImplicitParam(name = "size",paramType = "query", value = "分页条数", required = true ,dataType = "Integer"),
    })
    @GetMapping("/addressList")
    public ResultBodyData<PageEntity<AddressInfo>> addressList(HttpServletRequest request,@Param(value = "page") Integer page, @Param(value = "size")Integer size){

        String token = request.getHeader("token");
        List<TokenInfo> tokenInfoByToken = tokenRepository.findTokenInfoByToken(token);

        if (tokenInfoByToken == null || tokenInfoByToken.size()==0){
            throw  new CustomException(ErrorInfo.TOKEN_SESSION_ERROR);
        }

        return  new ResultBodyData<>(0,"Ok",addressService.getPageSort(tokenInfoByToken.get(0).getUserId(),page,size));

    }

}
