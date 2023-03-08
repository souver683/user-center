package com.light.usercenter.service;

import com.light.usercenter.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author 86550
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-02-28 19:57:31
*/
public interface UserService extends IService<User> {
    long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User desensitization(User user);
    int userLogout(HttpServletRequest request);
}
