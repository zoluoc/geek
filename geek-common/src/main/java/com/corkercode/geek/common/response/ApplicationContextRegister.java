package com.corkercode.geek.common.response;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Spring Application 注册
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 01:38
 */
@Component
public class ApplicationContextRegister implements ApplicationContextAware {

    private static ApplicationContext APPLICATION_CONTEXT;

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    /**
     * 设置spring上下文
     *
     * @param applicationContext spring上下文
     * @throws BeansException BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }
}
