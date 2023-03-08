package com.light.usercenter.commons;

import lombok.Data;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description 通用返回类
 * @data 2023/3/5 16 : 32 : 09
 */
@Data
public class BaseResponse <T>{
    private int code;
    private T data;
    private String message;
    private String description;

    public BaseResponse(int code, T data, String message,String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description=description;
    }

    public BaseResponse(int code, T data) {
        this(code,data,"","");
    }
    public BaseResponse(ErrorCode errorCode){
        this(errorCode.getCode(),null,errorCode.getMessage(),errorCode.getDescription());
    }
}
