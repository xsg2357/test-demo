package com.example.testdemo.exception;

public enum ErrorInfo {

    PARAM_NOT_EXIST(1001, "请添加参数"),
    NAME_NOT_EXIST(1003, "姓名不能为空"),
    EDUCATIONAL_NOT_EXIST(1004, "学历不能为空"),
    PHONE_NOT_EXIST(1005, "电话不能为空"),
    PHONE_NOT_ERROR(1020, "电话号码错误"),
    PWD_NOT_EXIST(1006, "密碼不能为空"),
    PWD_NEW_NOT_EXIST(1006, "新密碼不能为空"),
    AGE_NOT_EXIST(1007, "年龄不能为0"),
    DEPT_NOT_EXIST(1008, "部门不能为空"),
    GENDER_NOT_EXIST(1009, "性别不合法"),
    ID_NOT_EXIST(1010, "id不存在"),
    USER_EXIST(2001, "用戶已存在"),
    USER_NOT_EXIST(2002, "用戶不存在"),
    USER_PWD_NOT_EXIST(2003, "用戶名或者密碼錯誤"),
    PWD_ERROR_EXIST(2003, "密碼錯誤"),
    TOKEN_NOT_EXIST(2004, "token參數不存在或者為空"),
    TOKEN_ERROR(2005, "token失效或錯誤"),
    USER_ID_ERROR(2006, "userID错误"),
    USER_ERROR(2007, "用户错误信息，请联系后台"),
    TOKEN_SESSION_ERROR(2008, "token失效或Session錯誤"),
    LOGIN_ERROR(2009, "还没有登录"),
    AREA_NOT_EXIST(2010, "地质区域不能为空"),
    AREA_INFO_NOT_EXIST(2010, "地址信息不存在"),
    FILE_UPLOAD_FAILED(3010, "文件上传失败！！！"),
    FILE_NMAE_EMPTY(3011, "文件名为空！！！"),
    FILE_SIZE_OVER(3012, "上传文件过大,最大为50MB！！！"),
    PARAMS_ERROR(3016, "BODY参数为空或者错误"),
    PARAMS_VALUES_ERROR(3018, "参数值为或者错误"),
    PATH_PARAMS_VALUES_ERROR(3019, "参数值错误：PathId范围为1-4"),
    RESULT_EMPTY(3019, "查询结果为空"),
    RESULT_TRAFFIC_EMPTY(3020, "查询结果为空"),
    Category_ERROR(3017, "分类错误"),
    ADDRESS_NOT_EXIST(1002, "地址信息不能为空");

    public Integer code;
    public String message;

    private ErrorInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }



}
