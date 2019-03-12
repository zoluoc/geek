package com.corkercode.geek.common.interceptor;

import com.corkercode.geek.common.constant.ApiConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * http 请求拦截器
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 02:14
 */
@Slf4j
@Component
public class BasicHttpInterceptor implements HandlerInterceptor {

    private static final String CONTEXT_PATH_ROOT = "/";


    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    /**
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute(ApiConstant.API_BEGIN_TIME, System.currentTimeMillis());

        String method = request.getMethod();
        String requestUri = new UrlPathHelper().getOriginatingRequestUri(request);
        String contextPath = cleanContextPath(this.contextPath);
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(contextPath)) {
            requestUri = requestUri.replaceFirst(contextPath, "");
        }
        request.setAttribute(ApiConstant.API_REQURL, requestUri);
        request.setAttribute(ApiConstant.API_METHOD, method);
        return true;
    }

    /**
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    private String cleanContextPath(String contextPath) {
        if (StringUtils.hasText(contextPath) && contextPath.endsWith(CONTEXT_PATH_ROOT)) {
            return contextPath.substring(0, contextPath.length() - 1);
        }
        return contextPath;
    }

}
