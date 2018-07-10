package com.imooc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by XuQin on 2018/7/10.
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 转为json的String字符串
     * @param object
     * @return
     */
    public static String toJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json转成特定类型的对象
     * @param content
     * @param classType
     * @return
     */
    public static Object JsonToObject(String content, Class classType){
        try {
            return objectMapper.readValue(content, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json转成特定类型的对象
     * @param content
     * @param classType
     * @return
     */
    public static Object JsonToObject(String content, TypeReference classType){
        try {
            return objectMapper.readValue(content, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
