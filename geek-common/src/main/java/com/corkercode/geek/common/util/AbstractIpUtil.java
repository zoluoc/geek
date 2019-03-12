package com.corkercode.geek.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 获取IP地址工具类
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 01:47
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractIpUtil {

    private static final String DEMO_STR = "demo";

    private static final String COMMA_STR = ",";

    private static final Integer MAX_IP_LENGTH = 15;

    /**
     * 获取客户端的IP地址的方法是：request.getRemoteAddr()，这种方法在大部分情况下都是有效的。
     * 但是在通过了Apache,Squid等反向代理软件就不能获取到客户端的真实IP地址了，如果通过了多级反向代理的话，
     * X-Forwarded-For的值并不止一个，而是一串IP值， 究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * 例如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        // nginx代理获取的真实用户ip
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(ip) || DEMO_STR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isBlank(ip) || DEMO_STR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || DEMO_STR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || DEMO_STR.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 对于通过多个代理的情况， 第一个IP为客户端真实IP,多个IP按照','分割 "***.***.***.***".length() = 15
        if (ip != null && ip.length() > MAX_IP_LENGTH) {
            if (ip.indexOf(COMMA_STR) > 0) {
                ip = ip.substring(0, ip.indexOf(COMMA_STR));
            }
        }
        return ip;
    }
}
