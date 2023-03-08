package com.light.usercenter.service;

import com.light.usercenter.model.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author light
 * @version 1.0
 * @project user-center
 * @description
 * @data 2023/2/28 20 : 03 : 42
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;
    @Test
    public void testAdduser(){
        User user=new User();
        user.setUsername("light");
        user.setUserAccount("pc_worker_k");
        user.setAvatarUrl("https://cn.bing.com/images/search?q=%E5%A4%B4%E5%83%8F%E5%9B%BE%E7%89%87&FORM=IQFRBA&id=D73B373B6EF33A82C527D2E4D07FE0758A6DCEAD");
        user.setGender(1);
        user.setUserPassword("483452");
        user.setPhone("15586861291");
        user.setEmail("483452849@qq.com");
        user.setUserStatus(1);

        boolean result = userService.save(user);
        System.out.println(user.getId());
        assertTrue(result);
    }
    @Test
    void userRegister(){
        String userAccount="uz i";
        String userPassword="483452";
        String chechPassword="483452";
        String planetCode="12853";
        long result = userService.userRegister(userAccount, userPassword, chechPassword,planetCode);
        assertEquals(-1,result);
    }
}
