package com.corkercode.geek.common.util;

import com.corkercode.geek.common.constant.ApiConstant;
import com.corkercode.geek.common.enums.ErrorCodeEnum;
import com.corkercode.geek.common.model.ResponseWrapper;
import com.corkercode.geek.common.response.ApiResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

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
     * 获取异常信息
     *
     *
     * @param apiResponseBuilder
     * @param exception
     * @return
     */
    public static ApiResponse.ApiResponseBuilder<Object> exceptionMsg(ApiResponse.ApiResponseBuilder<Object> apiResponseBuilder, Exception exception) {
        if (exception instanceof MethodArgumentNotValidException) {
            StringBuilder builder = new StringBuilder("校验失败:");
            List<ObjectError> allErrors = ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors();
            allErrors.stream().findFirst().ifPresent(error -> {
                builder.append(((FieldError) error).getField()).append("字段规则为").append(error.getDefaultMessage());
                apiResponseBuilder.msg(error.getDefaultMessage());
            });
            apiResponseBuilder.result(builder.toString());
            return apiResponseBuilder;
        } else if (exception instanceof MissingServletRequestParameterException) {
            StringBuilder builder = new StringBuilder("参数字段");
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) exception;
            builder.append(ex.getParameterName());
            builder.append("校验不通过");
            apiResponseBuilder.result(builder.toString()).msg(ex.getMessage());
            return apiResponseBuilder;
        } else if (exception instanceof MissingPathVariableException) {
            StringBuilder builder = new StringBuilder("路径字段");
            MissingPathVariableException ex = (MissingPathVariableException) exception;
            builder.append(ex.getVariableName());
            builder.append("校验不通过");
            apiResponseBuilder.result(builder.toString()).msg(ex.getMessage());
            return apiResponseBuilder;
        } else if (exception instanceof ConstraintViolationException) {
            StringBuilder builder = new StringBuilder("方法.参数字段");
            ConstraintViolationException ex = (ConstraintViolationException) exception;
            Optional<ConstraintViolation<?>> first = ex.getConstraintViolations().stream().findFirst();
            if (first.isPresent()) {
                ConstraintViolation<?> constraintViolation = first.get();
                builder.append(constraintViolation.getPropertyPath().toString());
                builder.append("校验不通过");
                apiResponseBuilder.result(builder.toString()).msg(constraintViolation.getMessage());
            }
            return apiResponseBuilder;
        }
        apiResponseBuilder.result(AbstractTypeConvertUtil.castToString(exception));
        return apiResponseBuilder;
    }

    /**
     * 打印日志信息但是不输出到浏览器
     *
     * @param request
     * @param obj
     */
    public static void writeValAsJson(HttpServletRequest request, Object obj) {
        writeValAsJson(request, null, obj);
    }

    /**
     * Portal输出json字符串
     *
     * @param response
     * @param obj      需要转换JSON的对象
     */
    public static void writeValAsJson(HttpServletRequest request, ResponseWrapper response, Object obj) {
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

    /**
     * 发送错误信息
     *
     * @param request  request
     * @param response response
     * @param codeEnum codeEnum
     */
    public static void sendFail(HttpServletRequest request, HttpServletResponse response, ErrorCodeEnum codeEnum) {
        sendFail(request, response, codeEnum, null);
    }

    /**
     * 发送错误信息
     *
     * @param request  request
     * @param response response
     * @param codeEnum codeEnum
     */
    public static void sendFail(HttpServletRequest request, HttpServletResponse response, ErrorCodeEnum codeEnum,
                                Exception exception) {
        writeValAsJson(request, getWrapper(response, codeEnum), ApiResponse.failure(codeEnum, exception));
    }

    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 获取Response
     *
     * @return
     */
    public static ResponseWrapper getWrapper(HttpServletResponse response, ErrorCodeEnum errorCodeEnum) {
        return new ResponseWrapper(response, errorCodeEnum);
    }
}
