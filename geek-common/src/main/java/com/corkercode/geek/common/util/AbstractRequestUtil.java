package com.corkercode.geek.common.util;

import com.corkercode.geek.common.enums.HttpMethod;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * <p>
 * Request 请求工具类
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 01:44
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AbstractRequestUtil {
    /**
     * 判断请求方式GET
     *
     * @param request
     * @return boolean
     */
    public static boolean isGet(HttpServletRequest request) {
        return HttpMethod.GET.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式POST
     *
     * @param request
     * @return boolean
     */
    public static boolean isPost(HttpServletRequest request) {
        return HttpMethod.POST.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式PUT
     *
     * @param request
     * @return boolean
     */
    public static boolean isPut(HttpServletRequest request) {
        return HttpMethod.PUT.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式DELETE
     *
     * @param request
     * @return boolean
     */
    public static boolean isDelete(HttpServletRequest request) {
        return HttpMethod.DELETE.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式PATCH
     *
     * @param request
     * @return boolean
     */
    public static boolean isPatch(HttpServletRequest request) {
        return HttpMethod.PATCH.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式TRACE
     *
     * @param request
     * @return boolean
     */
    public static boolean isTrace(HttpServletRequest request) {
        return HttpMethod.TRACE.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式HEAD
     *
     * @param request
     * @return boolean
     */
    public static boolean isHead(HttpServletRequest request) {
        return HttpMethod.HEAD.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 判断请求方式OPTIONS
     *
     * @param request
     * @return boolean
     */
    public static boolean isOptions(HttpServletRequest request) {
        return HttpMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod());
    }

    /**
     * 获取请求
     *
     * @param request
     * @return 请求体
     */
    public static String getRequestBody(HttpServletRequest request) {
        String requestBody = null;
        if (isContainBody(request)) {
            try {
                ServletInputStream inputStream = request.getInputStream();
                if (Objects.nonNull(inputStream)) {
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
                    requestBody = writer.toString();
                }
            } catch (IOException ignored) {
            }
        }
        return requestBody;
    }

    /**
     * 获取请求
     *
     * @param request
     * @return 请求体字节流数据
     */
    public static byte[] getByteBody(HttpServletRequest request) {
        byte[] body = new byte[0];
        try {
            body = StreamUtils.copyToByteArray(request.getInputStream());
        } catch (IOException e) {
            log.error("Error: Get RequestBody byte[] fail," + "_" + e.getMessage(), e);
        }
        return body;
    }

    /**
     * 是否包含请求体
     *
     * @param request
     * @return boolean
     */
    public static boolean isContainBody(HttpServletRequest request) {
        return isPost(request) || isPut(request) || isPatch(request);
    }
}
