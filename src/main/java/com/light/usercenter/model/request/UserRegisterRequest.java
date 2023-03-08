package com.light.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description
 * @data 2023/3/1 20 : 00 : 26
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -8128274119356851065L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;

}
