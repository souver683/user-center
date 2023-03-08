package com.light.usercenter.commons;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description
 * @data 2023/3/5 16 : 36 : 35
 */
public class ResultUtil {
    public  static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0,data,"success","");
    }

    /**
     * 普通异常
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

    /**
     * 其他异常
     * @param code
     * @param message
     * @param description
     * @return
     */
    public static BaseResponse error(int code,String message,String description){
        return new BaseResponse<>(code,null,message,description);
    }

    /**
     * 系统异常
     * @param errorCode
     * @param message
     * @param description
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode,String message,String description){
        return new BaseResponse<>(errorCode.getCode(),null,message,description);
    }
}
