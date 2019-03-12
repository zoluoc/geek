package com.corkercode.geek.common.response;

import com.corkercode.geek.common.enums.ErrorCodeEnum;
import com.corkercode.geek.common.util.AbstractResponseUtil;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * T接口返回(多态) GET: 200 OK , POST: 201 Created , PUT: 200 OK , PATCH: 200 OK , DELETE: 204 No Content
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/12 22:56
 */
@Builder
@EqualsAndHashCode(callSuper = false)
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 5550806041953784875L;

    /**
     * http 状态码
     */
    private Integer status;

    /**
     * 错误描述
     */
    private String msg;

    /**
     * 结果集返回
     */
    private T result;

    /**
     * 当前时间戳
     */
    private LocalDateTime time;

    /**
     * 成功返回：仅返回成功响应状态
     *
     * @param status 响应状态
     */
    public static ApiResponse<Void> success(HttpServletResponse response, HttpStatus status) {
        response.setStatus(status.value());
        return ApiResponse.<Void>builder().status(status.value()).time(LocalDateTime.now()).build();

    }

    /**
     * 成功返回：返回状态值200及响应数据
     *
     * @param object 返回数据对象
     */
    public static <T> ApiResponse<T> success(HttpServletResponse response, T object) {
        return success(response, HttpStatus.OK, object);

    }

    /**
     * 成功返回：自定义状态值及响应数据
     *
     * @param status 响应状态
     * @param object 返回数据对象
     */
    public static <T> ApiResponse<T> success(HttpServletResponse response, HttpStatus status, T object) {
        response.setStatus(status.value());
        return ApiResponse.<T>builder().status(status.value()).result(object).time(LocalDateTime.now()).build();

    }

    /**
     * 失败返回：返回错误码及异常
     *
     * @param errorCodeEnum 错误枚举
     * @param exception     抛出异常
     */
    public static ApiResponse failure(ErrorCodeEnum errorCodeEnum, Exception exception) {

        return AbstractResponseUtil.exceptionMsg(ApiResponse.builder().msg(errorCodeEnum.msg()), exception)
                .time(LocalDateTime.now())
                .status(errorCodeEnum.httpCode())
                .build();

    }
}
