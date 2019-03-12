package com.corkercode.geek.common.util;

import com.corkercode.geek.common.constant.ApiConstant;
import com.corkercode.geek.common.enums.ErrorCodeEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * <p>
 * Response 输出工具类
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 01:34
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractResponseUtil {

    /**
     * 打印日志信息但是不输出到浏览器
     *
     * @param request
     * @param obj
     */
    public static void writeValAsJson(HttpServletRequest request, Object obj) {
        writeValAsJson(request, null, null, obj);
    }

    /**
     * Portal输出json字符串
     *
     * @param response
     * @param obj      需要转换JSON的对象
     */
    public static void writeValAsJson(HttpServletRequest request, HttpServletResponse response, ErrorCodeEnum codeEnum, Object obj) {
        if (Objects.nonNull(codeEnum)) {
            response.setStatus(codeEnum.httpCode());
        }
        AbstractLogUtil.printLog((Long) request.getAttribute(ApiConstant.API_BEGIN_TIME),
                AbstractTypeConvertUtil.castToString(request.getAttribute(ApiConstant.API_UID)),
                request.getParameterMap(),
                AbstractRequestUtil.getRequestBody(request),
                (String) request.getAttribute(ApiConstant.API_REQURL),
                (String) request.getAttribute(ApiConstant.API_METHOD),
                AbstractIpUtil.getIpAddr(request),
                obj);
        if (ObjectUtils.allNotNull(response, obj)) {
            writeValueAsJson(response, obj);
        }
    }

    /**
     * 向外输出json对象
     *
     * @param obj
     */
    private static void writeValueAsJson(HttpServletResponse response, Object obj) {
        if (response.isCommitted()) {
            log.warn("Warn: Response isCommitted, Skip the implementation of the method.");
            return;
        }
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (PrintWriter writer = response.getWriter()) {
            writer.print(AbstractJacksonUtil.toJson(obj));
            writer.flush();
        } catch (IOException e) {
            log.warn("Error: Response printJson faild, stackTrace: {}", getStackTraceAsString(e));
        }
    }

    private static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
