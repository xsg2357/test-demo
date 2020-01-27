package com.example.testdemo.controller;

import com.example.testdemo.domain.AddressInfo;
import com.example.testdemo.domain.PageEntity;
import com.example.testdemo.domain.ResultBodyData;
import com.example.testdemo.domain.TokenInfo;
import com.example.testdemo.exception.CustomException;
import com.example.testdemo.exception.ErrorInfo;
import com.example.testdemo.repo.AddressReposity;
import com.example.testdemo.repo.TokenRepository;
import com.example.testdemo.service.AddressService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Api(description = "地址信息")
@RequestMapping("/address")
@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    AddressReposity addressReposity;

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

        return  new ResultBodyData<>(0,"Ok",addressService.getPageSort(tokenInfoByToken.get(0).getUserId(),0,page,size));

    }


    @ApiOperation(value="添加地址", notes="添加地址信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query",value = "姓名", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "phone",paramType = "query", value = "联系电话", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "area",paramType = "query", value = "地址区域", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "address",paramType = "query", value = "详细地址", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "type",paramType = "query", value = "地址类型 待定义", required = true ,dataType = "int"),
            @ApiImplicitParam(name = "lat",paramType = "query", value = " 纬度 ", required = true ,dataType = "double"),
            @ApiImplicitParam(name = "lng",paramType = "query", value = "经度 ", required = true ,dataType = "double"),
    })
    @PostMapping("/addAddress")
    public ResultBodyData<String> addAddress(HttpServletRequest request,AddressInfo addressInfo){
        if (addressInfo == null){
            throw  new CustomException(ErrorInfo.ADDRESS_NOT_EXIST);
        }

        if (StringUtil.isNullOrEmpty(addressInfo.getName())){
            throw  new CustomException(ErrorInfo.NAME_NOT_EXIST);
        }
        if (StringUtil.isNullOrEmpty(addressInfo.getPhone())){
            throw  new CustomException(ErrorInfo.PHONE_NOT_EXIST);
        }
        if (StringUtil.isNullOrEmpty(addressInfo.getArea())){
            throw  new CustomException(ErrorInfo.AREA_NOT_EXIST);
        }

        String token = request.getHeader("token");
        List<TokenInfo> tokenInfoByToken = tokenRepository.findTokenInfoByToken(token);

        if (tokenInfoByToken == null || tokenInfoByToken.size()==0){
            throw  new CustomException(ErrorInfo.TOKEN_SESSION_ERROR);
        }

        AddressInfo addressInfoAdd = new AddressInfo();

        addressInfoAdd.setArea(addressInfo.getArea());
        addressInfoAdd.setType(addressInfo.getType());
        addressInfoAdd.setName(addressInfo.getName());
        addressInfoAdd.setPhone(addressInfo.getPhone());
        addressInfoAdd.setUserId(tokenInfoByToken.get(0).getUserId());
        addressInfoAdd.setCreateTime(new Date());
        if (!StringUtil.isNullOrEmpty(addressInfo.getAddress())){
            addressInfoAdd.setAddress(addressInfo.getAddress());
        }
        addressInfoAdd.setLat(addressInfo.getLat());
        addressInfoAdd.setLng(addressInfo.getLng());


        addressReposity.save(addressInfoAdd);

        return  new ResultBodyData<>(0,"Ok","");
    }
    @ApiOperation(value="修改地址", notes="修改地址信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "query",value = "地址Id", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "userId", paramType = "query",value = "用户名Id", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "name", paramType = "query",value = "姓名", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "phone",paramType = "query", value = "联系电话", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "area",paramType = "query", value = "地址区域", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "address",paramType = "query", value = "详细地址", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "type",paramType = "query", value = "地址类型 待定义", required = true ,dataType = "int"),
            @ApiImplicitParam(name = "lat",paramType = "query", value = " 纬度 ", required = true ,dataType = "double"),
            @ApiImplicitParam(name = "lng",paramType = "query", value = "经度 ", required = true ,dataType = "double"),
    })
    @PostMapping("/updateAddress")
    public ResultBodyData<String> updateAddress(AddressInfo addressInfo){
        if (addressInfo == null){
            throw  new CustomException(ErrorInfo.ADDRESS_NOT_EXIST);
        }

        if (addressInfo.getId() == 0){
            throw  new CustomException(ErrorInfo.AREA_INFO_NOT_EXIST);
        }
        if (addressInfo.getUserId() == 0){
            throw  new CustomException(ErrorInfo.USER_NOT_EXIST);
        }

        if (StringUtil.isNullOrEmpty(addressInfo.getName())){
            throw  new CustomException(ErrorInfo.NAME_NOT_EXIST);
        }
        if (StringUtil.isNullOrEmpty(addressInfo.getPhone())){
            throw  new CustomException(ErrorInfo.PHONE_NOT_EXIST);
        }
        if (StringUtil.isNullOrEmpty(addressInfo.getArea())){
            throw  new CustomException(ErrorInfo.AREA_NOT_EXIST);
        }


        AddressInfo addressInfoAdd = addressReposity.findAddressInfoById(addressInfo.getId());
        if (addressInfoAdd == null){
            throw  new CustomException(ErrorInfo.AREA_INFO_NOT_EXIST);
        }

        addressInfoAdd.setArea(addressInfo.getArea());
        addressInfoAdd.setType(addressInfo.getType());
        addressInfoAdd.setName(addressInfo.getName());
        addressInfoAdd.setPhone(addressInfo.getPhone());
        addressInfoAdd.setUserId(addressInfo.getUserId());
        addressInfoAdd.setCreateTime(new Date());
        if (!StringUtil.isNullOrEmpty(addressInfo.getAddress())){
            addressInfoAdd.setAddress(addressInfo.getAddress());
        }
        addressInfoAdd.setLat(addressInfo.getLat());
        addressInfoAdd.setLng(addressInfo.getLng());


        addressReposity.save(addressInfoAdd);

        return  new ResultBodyData<>(0,"Ok","");
    }


    @ApiOperation(value="删除地址", notes="删除地址信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", paramType = "query",value = "地址Id", required = true ,dataType = "string"),
            @ApiImplicitParam(name = "userId", paramType = "query",value = "用户名Id", required = true ,dataType = "string"),
    })
    @PostMapping("/delAddress")
    public ResultBodyData<String> delAddress(@Param("addressId") int addressId,@Param("userId") int userId){
        if (addressId == 0){
            throw  new CustomException(ErrorInfo.AREA_INFO_NOT_EXIST);
        }
        if (userId == 0){
            throw  new CustomException(ErrorInfo.USER_NOT_EXIST);
        }

        AddressInfo addressInfoAdd = addressReposity.findAddressInfoById(addressId);
        if (addressInfoAdd == null){
            throw  new CustomException(ErrorInfo.AREA_INFO_NOT_EXIST);
        }
        if (addressInfoAdd.getIsDelete() ==1){
            throw  new CustomException(ErrorInfo.AREA_INFO_NOT_EXIST);
        }

        addressInfoAdd.setIsDelete(1);
        addressReposity.save(addressInfoAdd);

        return  new ResultBodyData<>(0,"Ok","");
    }


}
