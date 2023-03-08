package com.light.usercenter.exception;

import com.light.usercenter.commons.ErrorCode;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description 全局异常
 * @data 2023/3/6 19 : 09 : 56
 */
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 6963108015040730221L;
    private  final int code;
    private  final String description;
    public BusinessException(int code,String message,String description){
        super(message);
        this.code=code;
        this.description=description;
    }
    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code=errorCode.getCode();
        this.description=errorCode.getDescription();
    }
    public BusinessException(ErrorCode errorCode,String description){
        super(errorCode.getMessage());
        this.code=errorCode.getCode();
        this.description=description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
