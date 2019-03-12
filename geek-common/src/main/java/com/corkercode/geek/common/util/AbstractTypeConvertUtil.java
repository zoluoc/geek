package com.corkercode.geek.common.util;

import com.corkercode.geek.common.exception.GeekException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * <p>
 * 类型转换工具类
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 01:43
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractTypeConvertUtil {

    private static final String TRUE_STR = "true";

    private static final String FALSE_STR = "false";

    private static final String COMMA_STR = ",";

    private static final Character COMMA_CHAR = ',';

    public static String castToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static Integer castToInt(Object value, Integer defaults) {
        Integer castToInt = castToInt(value);
        return Objects.isNull(castToInt) ? defaults : castToInt;

    }

    public static Boolean castToBoolean(Object value, Boolean defaults) {
        Boolean castToBoolean = castToBoolean(value);
        return Objects.isNull(castToBoolean) ? defaults : castToBoolean;

    }

    public static Integer castToInt(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Integer) {
            return (Integer) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue();
        }

        if (value instanceof String) {
            String strVal = (String) value;

            if (strVal.length() == 0
                    || "null".equals(strVal)
                    || "NULL".equals(strVal)) {
                return null;
            }

            if (strVal.indexOf(COMMA_CHAR) != 0) {
                strVal = strVal.replaceAll(COMMA_STR, "");
            }

            return Integer.parseInt(strVal);
        }

        if (value instanceof Boolean) {
            return (Boolean) value ? 1 : 0;
        }

        throw new GeekException("can not cast to int, value : " + value);
    }

    public static Boolean castToBoolean(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        if (value instanceof Number) {
            return ((Number) value).intValue() == 1;
        }

        if (value instanceof String) {
            String strVal = (String) value;

            if (strVal.length() == 0
                    || "null".equals(strVal)
                    || "NULL".equals(strVal)) {
                return null;
            }

            if (TRUE_STR.equalsIgnoreCase(strVal)
                    || "1".equals(strVal)) {
                return Boolean.TRUE;
            }

            if (FALSE_STR.equalsIgnoreCase(strVal)
                    || "0".equals(strVal)) {
                return Boolean.FALSE;
            }
        }

        throw new GeekException("can not cast to boolean, value : " + value);
    }
}
