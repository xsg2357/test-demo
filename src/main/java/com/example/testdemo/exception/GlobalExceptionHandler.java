package com.example.testdemo.exception;

import com.example.testdemo.domain.ResultBodyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.NOT_EXTENDED;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>(ex.getMessage(), NOT_EXTENDED);
    }

    @ExceptionHandler(value = Exception.class)
    public ResultBodyData<String> jsonHandler(HttpServletRequest request, Exception e) throws Exception {

        return new ResultBodyData<>(404,"出错请求"+request.getRequestURI(),"");
    }


    @ExceptionHandler(CustomException.class)
    public ResultBodyData<String> passwordMistake(CustomException e){
        log.error("错误信息："+e.getMessage());
        return new ResultBodyData<>(e.getCode(),e.getMsg(),"");
    }

}
