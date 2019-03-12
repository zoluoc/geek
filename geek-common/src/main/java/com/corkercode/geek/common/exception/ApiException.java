package com.corkercode.geek.common.exception;

import com.corkercode.geek.common.enums.ErrorCodeEnum;

/**
 * <p>
 * API 业务异常类
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 00:29
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorCodeEnum errorCodeEnum;

    public ApiException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.msg());
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }
}