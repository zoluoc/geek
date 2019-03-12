package com.corkercode.geek.common.exception;

/**
 * <p>
 * Geek 异常类
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 01:27
 */
public class GeekException extends RuntimeException {


    private static final long serialVersionUID = -8374543818779039249L;

    public GeekException(String message) {
        super(message);
    }

    public GeekException(Throwable throwable) {
        super(throwable);
    }

    public GeekException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
