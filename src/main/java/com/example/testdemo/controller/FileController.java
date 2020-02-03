package com.example.testdemo.controller;

import com.example.testdemo.constant.CommonConstant;
import com.example.testdemo.domain.ResultBodyData;
import com.example.testdemo.exception.CustomException;
import com.example.testdemo.exception.ErrorInfo;
import com.example.testdemo.service.DoUploadService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Api(description = "文件上传")
@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    DoUploadService doUploadService;

    /**
     * 接收上传的文件，并且将上传的文件存储在指定路径下
     */
    @ApiOperation(value="文件上传接口", notes="用于文件上传")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResultBodyData<Map<String, Object>> upload( @RequestParam("file") MultipartFile mPfile) {

    //判断是否允许普通成员上传文件
        int size = (int) mPfile.getSize();
        //50MB
        if (size > CommonConstant.FILE_UPLOAD_SIZE) {
            throw new CustomException(ErrorInfo.FILE_SIZE_OVER);
        }
        // 获取文件名
        String originalFilename = mPfile.getOriginalFilename();
        if (StringUtil.isNullOrEmpty(originalFilename)) {
            throw new CustomException(ErrorInfo.FILE_NMAE_EMPTY);
        }
        // 创建临时文件
        String path =  doUploadService.doUpload(mPfile);
        if (StringUtil.isNullOrEmpty(path)){
            throw  new CustomException(ErrorInfo.FILE_UPLOAD_FAILED);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("fileUrl",path);

        return  new ResultBodyData<>(0,"Ok",data);

    }
    /**
     *
     *
     * 接收上传的文件，并且将上传的文件存储在指定路径下
     */
    @ApiOperation(value="批量文件上传", notes="用于批量文件上传")
    @RequestMapping(value = "/uploadManyFile", method = RequestMethod.POST)
    public ResultBodyData<Map<String, Object>> upload( @RequestParam("file") MultipartFile[] mPfile) {

        if (mPfile == null || mPfile.length == 0){
            throw new CustomException(ErrorInfo.FILE_NMAE_EMPTY);
        }

        //判断是否允许普通成员上传文件
        StringBuilder listPath = new StringBuilder();
        for (int i = 0; i < mPfile.length ; i++) {
            if (mPfile[i] == null){
                throw new CustomException(ErrorInfo.FILE_NMAE_EMPTY);

            }
            int size = (int) mPfile[i].getSize();
            //50MB
            if (size > CommonConstant.FILE_UPLOAD_SIZE) {
                throw new CustomException(ErrorInfo.FILE_SIZE_OVER);
            }
            // 获取文件名
            String originalFilename = mPfile[i].getOriginalFilename();
            //批量传文件到后台
            if (StringUtil.isNullOrEmpty(originalFilename)) {
                throw new CustomException(ErrorInfo.FILE_NMAE_EMPTY);
            }
            // 创建临时文件   批量传文件到后台
            String path =  doUploadService.doUpload(mPfile);
            if (StringUtil.isNullOrEmpty(path)){
                throw  new CustomException(ErrorInfo.FILE_UPLOAD_FAILED);
            }
            listPath.append(path);
        }


        Map<String, Object> data = new HashMap<>();
        data.put("fileUrl",listPath);

        return  new ResultBodyData<>(0,"Ok",data);

    }

}
