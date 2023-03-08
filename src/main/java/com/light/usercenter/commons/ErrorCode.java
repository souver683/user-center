package com.light.usercenter.commons;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description 错误码
 * @data 2023/3/5 16 : 58 : 30
 */
public enum ErrorCode {

    SUCCESS(0,"success",""),
    PARAMS_ERROR(40000,"请求参数异常",""),
    NULL_ERROR(40001,"请求数据为空",""),
    NO_LOGIN(40100,"未登录",""),
    NO_AUTH(40101,"无权限",""),
    SYSTEM_ERROR(50000,"系统错误！",""),
    REPEAT_ERROR(50100,"账号重复！","");
    /**
     *状态码
     */
    private final int code;
    /**
     *状态码信息
     */
    private final String message;
    /**
     *状态码描述（详情）
     */
    private final String description;
    ErrorCode(int code,String message,String description){
        this.code=code;
        this.message=message;
        this.description=description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
