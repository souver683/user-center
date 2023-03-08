package com.light.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description
 * @data 2023/3/1 20 : 27 : 27
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 6059791237173322285L;
    private String userAccount;
    private String userPassword;
}
