package com.example.testdemo.exception;

import com.example.testdemo.domain.ResultBodyData;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorConfig implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public ResultBodyData<String> error(HttpServletResponse resp, HttpServletRequest req) {
        // 错误处理逻辑
        return new ResultBodyData<>(resp.getStatus(),"请求出错了","");
    }

}
