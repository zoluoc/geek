package com.corkercode.geek.upms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * TODO
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/2 16:12
 */
@Slf4j
@RequestMapping("user")
@RestController
public class UserController {

    @GetMapping("current")
    public String getCurrentUserInfo() {
        log.debug("geek-upms test......");
        return "admin";
    }
}
