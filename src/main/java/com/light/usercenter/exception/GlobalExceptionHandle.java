package com.light.usercenter.exception;

import com.light.usercenter.commons.BaseResponse;
import com.light.usercenter.commons.ErrorCode;
import com.light.usercenter.commons.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description 全局异常处理
 * @data 2023/3/6 19 : 30 : 56
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessExceptionHandle(BusinessException e){
        log.error("BusinessException",e.getMessage(),e);
        return ResultUtil.error(e.getCode(),e.getMessage(),e.getDescription());
    }
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandle(RuntimeException e){
        log.error("RuntimeException",e.getMessage(),e);
        return ResultUtil.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }

}
