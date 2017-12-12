package com.orca.common.utils.convert;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.*;
import java.util.Map.Entry;

/**
 * 处理json和对象的序列以及反序列化
 * @author master.yang
 */
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public abstract class JsonUtils {

    /**
     * 属性：private String operId
     * 转换结果中key与属性完全匹配， 例如：{"operId":"13818930251"}
     * 
     * 或者 private String OperId, 转换结果为: {"OperId":"13818930251"}"
     * 
     * 
     * @param obj
     * @return
     */
    public static String writeEntiry2JSON(Object obj) throws Exception {
        return writeEntiry2JSON(obj, null);
    }

    /**
     * 转换为json串时，属性名对应的key会通过命名策略进行转换
     * 命令策略有多种，比如使用将属性首字母转换为大写的策略
     * java类中属性 private String userName， 值为"13818930251"
     * 则json串中key就为{"UserName":"13818930251"}"
     * 不使用命名策略则属性值原样显示
     * 
     * @param obj 需要转换为json串的对象
     * @param propertyNamingStrategy 命名策略
     * @return
     * @throws Exception
     */
    public static String writeEntiry2JSON(Object obj,
                                          PropertyNamingStrategy.PropertyNamingStrategyBase propertyNamingStrategy)
                                                                                            throws Exception {
        Assert.notNull(obj, "obj is null");
        ObjectMapper objectMapper = new ObjectMapper();
        if (propertyNamingStrategy != null) {
            objectMapper.setPropertyNamingStrategy(propertyNamingStrategy);
        }
        return objectMapper.writeValueAsString(obj);
    }

    /**
     *
     * 属性：private String operId
     * 转换结果中key必须与属性完全匹配， 例如：{"operId":"13818930251"}"
     * 或者 private String OperId, jsonStr是：{"OperId":"13818930251"}"
     * 
     * @param jsonStr json字符串
     * @param clazz 需要转换的对象
     * @return
     */
    public static Object readJSON2Object(String jsonStr, Class clazz) throws Exception {
        return readJSON2Object(jsonStr, clazz, null);
    }

    /**
     * 通过属性的命名策略找到json串中的key
     * 命令策略有多种，比如java类中属性 private String userName
     * 而json串中key为{"UserName":"13818930251"}"
     * 则可以通过将属性首字母转换为大写，得到对应的值"13818930251"
     * 不使用命名策略则属性值必须完全匹配
     * 
     * @param jsonStr json字符串
     * @param clazz 需要转换的对象
     * @param propertyNamingStrategy 属性的命名策略
     * @return
     * @throws Exception
     */
    public static Object readJSON2Object(String jsonStr, Class clazz,
                                         PropertyNamingStrategy.PropertyNamingStrategyBase propertyNamingStrategy)
                                                                                           throws Exception {
        Assert.hasText(jsonStr, "jsonStr is empty");
        ObjectMapper objectMapper = new ObjectMapper();
        if (propertyNamingStrategy != null) {
            objectMapper.setPropertyNamingStrategy(propertyNamingStrategy);
        }
        return objectMapper.readValue(jsonStr, clazz);
    }

    /**
     * 通过字符串转换为MAP(以页面传递的参数为key 例如：{"OperId":"13818930251"}" key为OperId )
     * @param jsonStr
     * @return
     */
    public static Map<String, String> readJSON2Map(String jsonStr) throws Exception {
        Assert.hasText(jsonStr, "jsonStr is empty");

        JsonNode node = new ObjectMapper().readTree(jsonStr);
        Map<String, String> map = new HashMap<String, String>();
        Entry<String, JsonNode> jsonNode = null;
        for (Iterator<Entry<String, JsonNode>> iterator = node.fields(); iterator.hasNext();) {
            jsonNode = iterator.next();
            map.put(jsonNode.getKey(), jsonNode.getValue().asText());
        }
        return map;
    }

    /**
     * 将字符串转换为ArrayList
     * 
     * @param jsonStr
     * @param clazz
     * @return
     * @throws Exception
     */
    public static List readJson2ArrayList(String jsonStr, Class clazz) throws Exception {
        return readJson2ArrayList(jsonStr, clazz, null);
    }

    public static List readJson2ArrayList(String jsonStr, Class clazz,
                                          PropertyNamingStrategy.PropertyNamingStrategyBase propertyNamingStrategy)
                                                                                            throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        if (propertyNamingStrategy != null) {
            objectMapper.setPropertyNamingStrategy(propertyNamingStrategy);
        }

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
            clazz);
        return (List) objectMapper.readValue(jsonStr, javaType);
    }

    /**
     * 通过字符串获取指定的Json值 (以页面传递的参数为key 例如：{"OperId":"13818930251"}" key为OperId )
     * @param jsonStr
     * @param fieldName
     * @return
     */
    public static String findValueFromJsonStr(String jsonStr, String fieldName) throws Exception {
        Assert.hasText(jsonStr, "jsonStr is empty");
        Assert.hasText(fieldName, "fieldName is empty");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(jsonStr);
        JsonNode filed = node.findValue(fieldName);
        if (filed == null) {
            return StringUtils.EMPTY;
        }
        return filed.asText();
    }
}
