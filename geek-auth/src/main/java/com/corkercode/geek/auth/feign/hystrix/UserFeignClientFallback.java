package com.corkercode.geek.auth.feign.hystrix;

import com.corkercode.geek.auth.feign.UserFeignClient;
import org.springframework.stereotype.Component;

/**
 * <p>
 * TODO
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/3 18:46
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {

    @Override
    public String current() {
        return "获取当前用户错误！";
    }
}
