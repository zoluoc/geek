package com.corkercode.geek.common.util;

import com.corkercode.geek.common.exception.GeekException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * <p>
 * Jackson 工具类
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 01:33
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractJacksonUtil {

    private final static ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = initObjectMapper(new ObjectMapper());
    }

    /**
     * 获取ObjectMapper
     *
     * @return
     */
    private static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * 初始化 ObjectMapper
     *
     * @param objectMapper
     * @return
     */
    private static ObjectMapper initObjectMapper(ObjectMapper objectMapper) {
        if (Objects.isNull(objectMapper)) {
            objectMapper = new ObjectMapper();
        }
        return doInitObjectMapper(objectMapper);
    }

    /**
     * 初始化 ObjectMapper 时间方法
     *
     * @param objectMapper
     * @return
     */
    private static ObjectMapper doInitObjectMapper(ObjectMapper objectMapper) {
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //不显示为null的字段
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 忽略不能转移的字符
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        // 过滤对象的null属性.
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //忽略transient
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
        return registerModule(objectMapper);
    }

    /**
     * 注册模块
     *
     * @param objectMapper
     * @return
     */
    private static ObjectMapper registerModule(ObjectMapper objectMapper) {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

    /**
     * 转换Json
     *
     * @param object 数据对象
     * @return
     */
    public static String toJson(Object object) {
        if (isCharSequence(object)) {
            return (String) object;
        }
        try {
            return getObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new GeekException(e);
        }
    }

    /**
     * Json转换为对象 转换失败返回null
     *
     * @param json json数据
     * @return 实体对象
     */
    public static Object parse(String json) {
        Object object = null;
        try {
            object = getObjectMapper().readValue(json, Object.class);
        } catch (Exception ignored) {
        }
        return object;
    }

    /**
     * 是否为CharSequence类型
     *
     * @param object 数据对象
     * @return true/false
     */
    private static Boolean isCharSequence(Object object) {
        return !Objects.isNull(object) && isCharSequence(object.getClass());
    }

    private static boolean isCharSequence(Class<?> clazz) {
        return clazz != null && CharSequence.class.isAssignableFrom(clazz);
    }
}
