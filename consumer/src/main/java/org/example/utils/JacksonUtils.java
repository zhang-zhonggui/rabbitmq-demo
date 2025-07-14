package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class JacksonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // 基础配置
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new JavaTimeModule());
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * 对象转 JSON 字符串并处理可能的异常
     */
    public static Optional<String> toJsonString(Object obj) {
        try {
            return Optional.of(mapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * JSON 字符串转对象并处理可能的异常
     */
    public static <T> Optional<T> fromJson(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(mapper.readValue(json, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * JSON 字符串转泛型对象（如 Map、List 等）并处理可能的异常
     */
    public static <T> Optional<T> parseObject(String json, TypeReference<T> typeReference) {
        if (json == null || json.isEmpty()) {
            return Optional.empty();
        }
        try {
            return Optional.of(mapper.readValue(json, typeReference));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * JSON 字符串转泛型集合（如 List<User>）并处理可能的异常
     */
    public static <T> Optional<T> parseArray(String json, Class<?> collectionClass, Class<?> elementClass) {
        if (json == null || json.isEmpty()) {
            return Optional.empty();
        }
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
            return Optional.of(mapper.readValue(json, javaType));
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * 对象转 Map（用于动态处理 JSON）并处理可能的异常
     */
    public static Optional<Map<String, Object>> toMap(Object obj) {
        try {
            return Optional.of(mapper.convertValue(obj, Map.class));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Map 转对象并处理可能的异常
     */
    public static <T> Optional<T> toObject(Map<String, Object> map, Class<T> clazz) {
        try {
            return Optional.of(mapper.convertValue(map, clazz));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}