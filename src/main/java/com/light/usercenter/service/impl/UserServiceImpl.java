package com.light.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.light.usercenter.commons.BaseResponse;
import com.light.usercenter.commons.ErrorCode;
import com.light.usercenter.commons.ResultUtil;
import com.light.usercenter.exception.BusinessException;
import com.light.usercenter.service.UserService;
import com.light.usercenter.model.domain.User;
import com.light.usercenter.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.light.usercenter.constant.constant.SALT;
import static com.light.usercenter.constant.constant.USER_LOGIN_STATUS;

/**
* @author 86550
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-02-28 19:57:31
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode) {
        //1. 校验账号密码
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            throw  new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (userAccount.length()<4){
            throw   new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度太短！");
        }
        if (userPassword.length()<8||checkPassword.length()<8){
            throw   new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度太短！");
        }
        if (planetCode.length()>5){
            throw  new BusinessException(ErrorCode.PARAMS_ERROR,"星球编号过长！");
        }
        //2.账号不能包含特殊字符
        String validPattern ="[\\\\u00A0\\\\s\\\"`~!@#$%^&* ()+=| {}':;',\\\\ [\\\\].<>/?~！ @#￥%……&* ()——+| {}【】‘；：”“'。 ，、？ ]  ";
        Matcher matcher= Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            throw   new BusinessException(ErrorCode.PARAMS_ERROR,"账号包含特殊字符！");
        }
        //3.1校验账号是否重复
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
//        LambdaQueryWrapper<User> userLambdaQueryWrapper=new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.eq(User::getUserAccount,userAccount);
        long count = this.count(queryWrapper);
        if (count>=1){
            throw new BusinessException(ErrorCode.REPEAT_ERROR,"账号不能重复！");
        }
        //3.2校验星球编号是否重复
        queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("planetCode",planetCode);
            count = this.count(queryWrapper);
        if (count>=1){
            throw new BusinessException(ErrorCode.REPEAT_ERROR,"星球账号不能重复！");
        }
        //4.校验密码
        if (!userPassword.equals(checkPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次密码输入不一致！");
        }
        //5.加密密码
        String encrypPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //6.插入数据
        User user =new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encrypPassword);
        user.setPlanetCode(planetCode);
        boolean result = this.save(user);
        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"保存失败！");
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //Todo 账户异常处理
        //1. 校验账号密码
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码不能为空！");
        }
        if (userAccount.length()<4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号长度太短！");
        }
        if (userPassword.length()<8){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度太短！");
        }
        //2.账号不能包含特殊字符
        String validPattern ="[\\\\u00A0\\\\s\\\"`~!@#$%^&* ()+=| {}':;',\\\\ [\\\\].<>/?~！ @#￥%……&* ()——+| {}【】‘；：”“'。 ，、？ ]  ";
        Matcher matcher= Pattern.compile(validPattern).matcher(userAccount);
        if(matcher.find()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"账号不能包含特殊字符！");
        }
        //3.校验账号
        String encrypPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userAccount",userAccount);
        queryWrapper.eq("userPassword",encrypPassword);
        User user = this.getOne(queryWrapper);
        //用户信息脱敏
        User newuser = desensitization(user);
        request.getSession().setAttribute(USER_LOGIN_STATUS,newuser);
        return newuser;
    }
    @Override
    public User desensitization(User user){
        User newuser =new User();
        if (user==null){
            return null;
        }
        newuser.setId(user.getId());
        newuser.setUsername(user.getUsername());
        newuser.setUserAccount(user.getUserAccount());
        newuser.setAvatarUrl(user.getAvatarUrl());
        newuser.setGender(user.getGender());
        newuser.setPhone(user.getPhone());
        newuser.setUserPassword(null);
        newuser.setEmail(user.getEmail());
        newuser.setUserStatus(user.getUserStatus());
        newuser.setCreateTime(user.getCreateTime());
        newuser.setUserRole(user.getUserRole());
        newuser.setPlanetCode(user.getPlanetCode());
        return newuser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_STATUS);
        return 1;
    }
}




