package com.corkercode.geek.common.util;

import com.corkercode.geek.common.model.Log;
import com.corkercode.geek.common.spring.ApplicationUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 请求日志工具类
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 01:31
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractLogUtil {

    /**
     * 获取日志对象
     *
     * @param beiginTime   开始时间
     * @param parameterMap 参数map集
     * @param requestBody  请求体
     * @param url          请求路径
     * @param method       请求方法
     * @param ip           请求ip地址
     */
    public static void printLog(Long beiginTime, String uid, Map<String, String[]> parameterMap,
                                String requestBody, String url, String method, String ip, Object object) {
        Log logInfo = Log.builder()
                //查询参数
                .parameterMap(parameterMap)
                .uid(uid)
                //请求体
                .requestBody(Optional.ofNullable(AbstractJacksonUtil.parse(requestBody)).orElse(requestBody))
                //请求路径
                .url(url)
                //请求方法
                .method(method)
                .runTime((beiginTime != null ? System.currentTimeMillis() - beiginTime : 0) + "ms")
                .result(object)
                .ip(ip)
                .build();
        log.info(AbstractJacksonUtil.toJson(logInfo));
    }

    public static void doAfterReturning(Object ret) {
        AbstractResponseUtil.writeValAsJson(ApplicationUtil.getRequest(), ret);
    }
}
