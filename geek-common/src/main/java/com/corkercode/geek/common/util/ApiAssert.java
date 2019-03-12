package com.corkercode.geek.common.util;

import com.corkercode.geek.common.enums.ErrorCodeEnum;
import com.corkercode.geek.common.exception.ApiException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * API 断言
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 00:01
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ApiAssert {

    /**
     * 断言两个对象不相等，就出抛异常
     *
     * @param errorCodeEnum 错误码
     * @param obj1          对象
     * @param obj2          对象
     */
    public static void equals(ErrorCodeEnum errorCodeEnum, Object obj1, Object obj2) {
        if (!Objects.equals(obj1, obj2)) {
            failure(errorCodeEnum);
        }
    }

    /**
     * 断言是 False 就出抛异常
     *
     * @param errorCodeEnum 错误码
     * @param condition     条件
     */
    public static void isTrue(ErrorCodeEnum errorCodeEnum, boolean condition) {
        if (!condition) {
            failure(errorCodeEnum);
        }
    }

    /**
     * 断言是 True 就出抛异常
     *
     * @param errorCodeEnum 错误码
     * @param condition     条件
     */
    public static void isFalse(ErrorCodeEnum errorCodeEnum, boolean condition) {
        if (condition) {
            failure(errorCodeEnum);
        }
    }

    /**
     * 断言对象为空，就出抛异常
     *
     * @param errorCodeEnum 错误码
     * @param conditions    对象
     */
    public static void isNotNull(ErrorCodeEnum errorCodeEnum, Object... conditions) {
        if (!ObjectUtils.allNotNull(conditions)) {
            failure(errorCodeEnum);
        }
    }

    /**
     * Assert that an array has no null elements. Note: Does not complain if the array is empty!
     * Assert.noNullElements(array, &quot;The array must have non-null elements&quot;);
     *
     * @param array         the array to check
     * @param errorCodeEnum the exception message to use if the assertion fails
     */
    public static void noNullElements(ErrorCodeEnum errorCodeEnum, Object[] array) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    failure(errorCodeEnum);
                }
            }
        }
    }

    /**
     * Assert that a array has elements; that is, it must not be {@code null} and must have at least one element.
     * Assert.notEmpty(array, &quot;array must have elements&quot;);
     *
     * @param array    the array to check
     * @param errorCodeEnum the exception message to use if the assertion fails
     * @throws ApiException if the array is {@code null} or has no elements
     */
    public static void notEmpty(ErrorCodeEnum errorCodeEnum, Object[] array) {
        if (null != array && array.length == 0) {
            failure(errorCodeEnum);
        }
    }

    /**
     * Assert that a collection has elements; that is, it must not be {@code null} and must have at least one element.
     * Assert.notEmpty(collection, &quot;Collection must have elements&quot;);
     *
     * @param collection    the collection to check
     * @param errorCodeEnum the exception message to use if the assertion fails
     * @throws ApiException if the collection is {@code null} or has no elements
     */
    public static void notEmpty(ErrorCodeEnum errorCodeEnum, Collection<?> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            failure(errorCodeEnum);
        }
    }

    /**
     * Assert that a Map has entries; that is, it must not be {@code null} and must have at least one entry.
     * Assert.notEmpty(map, &quot;Map must have entries&quot;);
     *
     * @param map           the map to check
     * @param errorCodeEnum the exception message to use if the assertion fails
     * @throws ApiException if the map is {@code null} or has no entries
     */
    public static void notEmpty(ErrorCodeEnum errorCodeEnum, Map<?, ?> map) {
        if (MapUtils.isEmpty(map)) {
            failure(errorCodeEnum);
        }
    }

    /**
     * 封装失败结果
     *
     * @param errorCodeEnum 异常错误码
     */
    private static void failure(ErrorCodeEnum errorCodeEnum) {
        throw new ApiException(errorCodeEnum);
    }
}
