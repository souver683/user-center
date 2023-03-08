package com.light.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.light.usercenter.commons.BaseResponse;
import com.light.usercenter.commons.ErrorCode;
import com.light.usercenter.commons.ResultUtil;
import com.light.usercenter.exception.BusinessException;
import com.light.usercenter.model.domain.User;
import com.light.usercenter.model.request.UserLoginRequest;
import com.light.usercenter.model.request.UserRegisterRequest;
import com.light.usercenter.service.UserService;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.light.usercenter.constant.constant.USER_LOGIN_STATUS;
import static com.light.usercenter.constant.constant.isAdmin;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description
 * @data 2023/3/1 20 : 17 : 25
 */
@RestController
@RequestMapping("/user")
public class userController {
    @Resource
    private UserService userService;
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);

        }
        String userAccount=userRegisterRequest.getUserAccount();
        String userPassword=userRegisterRequest.getUserPassword();
        String checkPassword=userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数异常！");
        }
        long l = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtil.success(l);
    }
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object attributeUser = request.getSession().getAttribute(USER_LOGIN_STATUS);
        User objUser=(User) attributeUser;
        if (objUser==null){
            return null;
        }
        Long id = objUser.getId();
        User user = userService.getById(id);

        return  ResultUtil.success(userService.desensitization(user));
    }
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount=userLoginRequest.getUserAccount();
        String userPassword=userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtil.success(userService.userLogin(userAccount, userPassword,request));
    }
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if (request==null){

        }
        return ResultUtil.success(userService.userLogout(request));
    }
    @GetMapping("/search")
    public BaseResponse<List<User>> userSearch(String username,HttpServletRequest request){
        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_LOGIN,"请先登录！");
        }
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (!StringUtils.isAnyBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> list = userService.list(queryWrapper);
        List<User> collect = list.stream().map(user -> userService.desensitization(user)).collect(Collectors.toList());
        return ResultUtil.success(collect);
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> userDelete(@RequestBody long id,HttpServletRequest request){
        if (!isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_LOGIN,"请先登录！");
        }
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id", id);
        boolean remove = userService.remove(queryWrapper);
        return ResultUtil.success(remove);
    }


    private boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATUS);
        User user=(User)userObj;
        if (user==null&&user.getUserRole()!=isAdmin){
            return false;
        }
        return  true;
    }


}



