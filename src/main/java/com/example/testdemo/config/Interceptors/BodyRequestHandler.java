package com.example.testdemo.config.Interceptors;

import com.example.testdemo.config.filter.BodyRequestWrapper;
import com.example.testdemo.exception.CustomException;
import com.example.testdemo.exception.ErrorInfo;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class BodyRequestHandler implements HandlerInterceptor {

    //日志操作
    private static final Logger log = LoggerFactory.getLogger(BodyRequestHandler.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        ///获取请求方式
        String method = request.getMethod();
        ///请求地址
        String url = request.getRequestURI();
        ///获取请求参数
        String paramsStr = "";
        if ("POST".equals(method)) {
            ///获取post请求参数
            paramsStr = this.getPostParam(request);
        } else if ("GET".equals(method)) {
            //Get方式请求参数
            paramsStr = request.getQueryString();
        }
        ///日志模板
        String Template = "请求方式：%s ；请求地址：%s ；请求参数：%s 。";
        ///记录日志
        log.info(String.format(Template, method, url, paramsStr));
        if (StringUtil.isNullOrEmpty(paramsStr) || paramsStr.equals("-1")){
            throw  new CustomException(ErrorInfo.PARAMS_ERROR);
        }
        return true;
    }

    private String getPostParam(HttpServletRequest request) {
        try {
            BodyRequestWrapper bodyReaderWrapper = new BodyRequestWrapper(request);
            return getPostBodyParams(bodyReaderWrapper.getInputStream(), request.getCharacterEncoding());
        } catch (Exception e) {
            ///记录日志
            log.error("getPostParm exception", e);
            return "-1";
        }
    }

    /**
     * 获取POST请求中Body参数
     */
    private String getPostBodyParams(ServletInputStream inputStream, String charset) throws Exception {
        try {

            String body = StreamUtils.copyToString(inputStream, Charset.forName(charset));
            if (StringUtil.isNullOrEmpty(body)) {
                return "";
            }
            return body;
        } catch (Exception e) {
            return  "-1";
        }
    }


}
