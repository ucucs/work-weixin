package com.ucucs.wxwork.module.util;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

/**
 * JSON处理帮助类.
 *
 * @author ucucs.
 */
public class JsonUtil {

  private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

  private static final ObjectMapper objectMapper = new ObjectMapper();

  /** 日期默认格式化. */
  private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

  static {
    // 对象的所有字段全部列入
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    // 取消默认转换timestamps形式
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    // 忽略空Bean转json的错误
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    // 所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss
    objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
    // 忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static ObjectNode createNode() {
    return objectMapper.createObjectNode();
  }

  public static ObjectMapper getMapper() {
    return objectMapper.copy();
  }

  /**
   * 对象转Json格式字符串.
   *
   * @param obj 对象
   * @return Json格式字符串
   */
  public static <T> String toJson(T obj) {
    if (obj == null) {
      return null;
    }
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      logger.warn("Serialize Object to String error : {}", e.getMessage());
      return null;
    }
  }

  /**
   * 对象转Json格式字符串,美化输出.
   *
   * @param obj 对象
   * @return 美化的Json格式字符串
   */
  public static <T> String toJsonPretty(T obj) {
    if (obj == null) {
      return null;
    }
    try {
      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      logger.warn("Serialize Object to String error : {}", e.getMessage());
      return null;
    }
  }

  /**
   * 对象转Json格式字符串,过滤特定属性.
   *
   * @param obj 对象
   * @param excludeProperties 过滤属性字符串数组
   * @param <T> 对象类型
   * @return 过滤后的Json格式字符串
   */
  public static <T> String toJsonWithExclude(T obj, String[] excludeProperties) {
    try {
      return objectMapper
          .writer(
              new SimpleFilterProvider()
                  .addFilter(
                      AnnotationUtils.getValue(
                              AnnotationUtils.findAnnotation(obj.getClass(), JsonFilter.class))
                          .toString(),
                      SimpleBeanPropertyFilter.serializeAllExcept(excludeProperties)))
          .writeValueAsString(obj);
    } catch (Exception ex) {
      logger.error("Serialize Object To Json Failed:{} {} {}", obj, excludeProperties, ex);
    }

    return null;
  }

  public static JsonNode fromJson(String str) {
    if (StringUtils.isEmpty(str)) {
      return null;
    }
    try {
      return objectMapper.readTree(str);
    } catch (Exception e) {
      logger.warn("Parse String to Object error : {}", e.getMessage());
      return null;
    }
  }

  /**
   * 字符串转换为指定对象.
   *
   * @param str JSON字符串
   * @param clazz 指定对象的class
   * @return 指定对象
   */
  public static <T> T fromJson(String str, Class<T> clazz) {
    if (StringUtils.isEmpty(str) || clazz == null) {
      return null;
    }
    try {
      return objectMapper.readValue(str, clazz);
    } catch (Exception e) {
      logger.warn("Parse String to Object error : {}", e.getMessage());
      return null;
    }
  }

  /**
   * 字符串转换为指定对象.
   *
   * @param str JSON字符串
   * @param typeReference 类型引用
   * @param <T> 指定对象类型
   * @return 指定对象
   */
  public static <T> T fromJson(String str, TypeReference<T> typeReference) {
    if (StringUtils.isEmpty(str) || typeReference == null) {
      return null;
    }
    try {
      return objectMapper.readValue(str, typeReference);
    } catch (IOException e) {
      logger.warn("Parse String to Object error", e);
      return null;
    }
  }

  /**
   * 字符串转换成指定类型集合对象.
   *
   * @param str JSON字符串
   * @param collectionClass 集合类型的class
   * @param elementClass 元素类型的class
   * @param <T> 指定类型
   * @return 集合对象
   */
  public static <T> T jsonToList(
      String str, Class<? extends Collection<?>> collectionClass, Class<?> elementClass) {
    try {
      JavaType javaType = createCollectionType(collectionClass, elementClass);
      return objectMapper.readValue(str, javaType);
    } catch (IOException e) {
      logger.warn("Parse String to Collection Type Object error : {}" + e.getMessage());
      return null;
    }
  }

  public static <T> List<T> fromJsonToList(String jsonArrayString, Class<T> clazz) {
    try {
      if (StringUtils.isEmpty(jsonArrayString)) {
        return null;
      }

      JavaType javaType = createCollectionType(ArrayList.class, clazz);
      return objectMapper.readValue(jsonArrayString, javaType);
    } catch (Exception ex) {
      logger.error("DeSerialize Object Json Failed:{} {} {}", jsonArrayString, clazz, ex);
    }

    return null;
  }

  public static JavaType createCollectionType(
      Class<?> collectionClass, Class<?>... elementClasses) {
    return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
  }

  /**
   * 字符串转换成指定类型字典对象.
   *
   * @param str JSON字符串
   * @param mapClass 字典类型的class
   * @param keyClass 键类型的class
   * @param valueClass 值类型的class
   * @param <T> 指定类型
   * @return 字典对象
   */
  public static <T> T jsonToMap(
      String str, Class<? extends Map<?, ?>> mapClass, Class<?> keyClass, Class<?> valueClass) {
    try {
      JavaType javaType = createMapType(mapClass, keyClass, valueClass);
      return objectMapper.readValue(str, javaType);
    } catch (IOException e) {
      logger.warn("Parse String to Map Type Object error : {}" + e.getMessage());
      return null;
    }
  }

  public static Map<String, Object> beanToMap(Object object) {
    try {
      return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
    } catch (Exception e) {
      logger.warn("Parse String to Map Type Object error : {}" + e.getMessage());
      return null;
    }
  }

  /**
   * 字典类型转换成对象.
   *
   * @param mapSource 字典数据
   * @param valueClass 对象类型的class
   * @param <T> 指定类型
   * @return 指定类型对象
   */
  public static <T> T mapToBean(Map<?, ?> mapSource, Class<T> valueClass) {
    return objectMapper.convertValue(mapSource, valueClass);
  }

  public static <T> T nodeToBean(JsonNode jsonNode, Class<T> valueClass) {
    return objectMapper.convertValue(jsonNode, valueClass);
  }

  public static <T> List<T> nodeToBeanList(ArrayNode arrayNode, Class<T> valueClass) {
    List<T> valueList = new ArrayList<>();
    for (JsonNode jsonNode : arrayNode) {
      valueList.add(objectMapper.convertValue(jsonNode, valueClass));
    }
    return valueList;
  }

  /**
   * 创建复合集合类型.
   *
   * @param collectionClass 集合类型的class
   * @param elementClass 元素类型的class
   * @return 复合类型
   */
  public static JavaType createCollectionType(
      Class<? extends Collection<?>> collectionClass, Class<?> elementClass) {
    return objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
  }

  /**
   * 创建复合字典类型.
   *
   * @param mapClass 字典类型的class
   * @param keyClass 键类型的class
   * @param valueClass 值类型的class
   * @return 字典类型
   */
  public static JavaType createMapType(
      Class<? extends Map<?, ?>> mapClass, Class<?> keyClass, Class<?> valueClass) {
    return objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
  }
}
