package com.corkercode.geek.auth.controller;

import com.corkercode.geek.auth.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * TODO
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/2 16:26
 */
@RequestMapping("auth")
@RestController
public class AuthController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("current")
    public String getCurrentUserInfo() {
        return userFeignClient.current();
    }
}
