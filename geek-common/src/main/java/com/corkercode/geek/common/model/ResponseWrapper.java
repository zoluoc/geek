package com.corkercode.geek.common.model;

import com.corkercode.geek.common.enums.ErrorCodeEnum;
import com.corkercode.geek.common.response.ApiResponse;
import com.corkercode.geek.common.util.AbstractJacksonUtil;
import com.corkercode.geek.common.util.AbstractResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * <p>
 * response 包装类
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 02:20
 */
@Slf4j
public class ResponseWrapper extends HttpServletResponseWrapper {

    private ErrorCodeEnum errorCodeEnum;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public ResponseWrapper(HttpServletResponse response,  ErrorCodeEnum errorCodeEnum) {
        super(response);
        setErrorCodeEnum(errorCodeEnum);
    }

    /**
     * 获取ErrorCode
     *
     * @return
     */
    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    /**
     * 设置ErrorCode
     *
     * @param errorCodeEnum
     */
    public void setErrorCodeEnum( ErrorCodeEnum errorCodeEnum) {
        if (Objects.nonNull(errorCodeEnum)) {
            this.errorCodeEnum = errorCodeEnum;
            super.setStatus(this.errorCodeEnum.httpCode());
        }
    }

    /**
     * 向外输出错误信息
     *
     * @param e
     * @throws IOException
     */
    public void writerErrorMsg(Exception e) {
        if (Objects.isNull(errorCodeEnum)) {
            log.warn("Warn: ErrorCodeEnum cannot be null, Skip the implementation of the method.");
            return;
        }
        printWriterApiResponses(ApiResponse.failure(this.getErrorCodeEnum(), e));
    }

    /**
     * 设置ApiErrorMsg
     */
    public void writerErrorMsg() {
        writerErrorMsg(null);
    }

    /**
     * 向外输出ApiResponses
     *
     * @param apiResponses
     */
    public void printWriterApiResponses(ApiResponse apiResponses) {
        writeValueAsJson(apiResponses);
    }

    /**
     * 向外输出json对象
     *
     * @param obj
     */
    public void writeValueAsJson(Object obj) {
        if (super.isCommitted()) {
            log.warn("Warn: Response isCommitted, Skip the implementation of the method.");
            return;
        }
        super.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        super.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (PrintWriter writer = super.getWriter()) {
            writer.print(AbstractJacksonUtil.toJson(obj));
            writer.flush();
        } catch (IOException e) {
            log.warn("Error: Response printJson faild, stackTrace: {}", AbstractResponseUtil.getStackTraceAsString(e));
        }
    }

}
