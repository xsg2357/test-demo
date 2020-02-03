package com.example.testdemo.service;

import com.example.testdemo.util.FtpProperties;
import com.example.testdemo.util.UploadFileStatus;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Locale;
import java.util.Random;

@Service
public class DoUploadService {

    @Autowired
    FtpProperties ftpProperties;

    public String doUpload(MultipartFile multipartFile) {
        try {
            //老文件名
            String oldFileName = multipartFile.getOriginalFilename();

            //根据id调用工具类生成新文件名
            String newFileName = UploadFileStatus.getFileName(new Random().nextInt(9999));
            //截取老文件名的后缀
            String substring = oldFileName.substring(oldFileName.lastIndexOf("."));
            //将后缀放在新文件名的后面
            newFileName = newFileName + substring;
            //生成路径
            String filePath = new DateTime().toString("yyyy/MM/dd", Locale.CHINA);
            //上传
            Boolean resultBoolean = UploadFileStatus.uploadFile(0,ftpProperties.getHost(), Integer.parseInt(ftpProperties.getPort()), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, multipartFile.getInputStream());
            //判断是否上传成功
            if (resultBoolean) {
                //上传成功
                //如果更新成功
                System.out.println(ftpProperties.getHttpPath() + File.separator + filePath + File.separator + newFileName);
                return ftpProperties.getHttpPath() + File.separator + filePath + File.separator + newFileName;
            } else {
                //如果没有上传成功
                return "";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "";
        }
    }


    public String doUpload(MultipartFile[] multipartFiles) {
        String headAll = "";
        //  取出session中存的用户user
        //取出user中的id
        //根据id调用工具类生成新文件名
        String newFileName = UploadFileStatus.getFileName(new Random().nextInt(9999));

        //对文件数组进行遍历
        for (MultipartFile multipartFile : multipartFiles) {
            //老文件名
            String oldFileName = multipartFile.getOriginalFilename();
            //截取老文件名的后缀
            String substring = oldFileName.substring(oldFileName.lastIndexOf("."));
            //将后缀放在新文件名的后面
            newFileName = newFileName + substring;
            //生成路径
            String filePath = new DateTime().toString("yyyy/MM/dd", Locale.CHINA);
            //上传
            try {
                Boolean resultBoolean = UploadFileStatus.uploadFile(1,ftpProperties.getHost(), Integer.parseInt(ftpProperties.getPort()),
                        ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, multipartFile.getInputStream());
                if (resultBoolean) {
                    //上传成功
                    String head = ftpProperties.getHttpPath() + File.separator + filePath + File.separator + newFileName;
                    //将当前图片的地址加进headAll，组合成一个新字符串,每个地址中间用\隔开
                    headAll = headAll + ";" + head;


                } else {
                    //如果没有上传成功
                    return "";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }

        }
        return headAll;
    }
}
