package com.light.usercenter.constant;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description
 * @data 2023/3/2 15 : 17 : 50
 */
public interface constant {
    /**
     * 盐值
     */
    String SALT="light";
    /**
     * 用户登录状态
     */
    String USER_LOGIN_STATUS="userLoginStatus";
    /**
     * 角色 0-普通用户 1-管理员
     */
    Integer isAdmin=1;
    Integer noAdmin=0;

}
