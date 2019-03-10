package com.corkercode.geek.auth.feign;

import com.corkercode.geek.auth.feign.hystrix.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * TODO
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/2 16:24
 */
@FeignClient(name = "geek-upms", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/user/current")
    String current();
}
