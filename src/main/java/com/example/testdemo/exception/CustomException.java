package com.example.testdemo.exception;

public class CustomException extends RuntimeException{

    private  int code;
    private String msg;

    public CustomException(ErrorInfo errorInfo){
        super(errorInfo.message);
        this.code = errorInfo.code;
        this.msg = errorInfo.message;
    }

    public CustomException(int code, String msg){
        super(msg);
        this.code = code;
        this.msg =msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
