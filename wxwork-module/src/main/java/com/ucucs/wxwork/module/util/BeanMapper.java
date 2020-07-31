package com.ucucs.wxwork.module.util;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

/** 对象映射复制帮助类. */
public class BeanMapper {

  private static final Logger LOG = LoggerFactory.getLogger(BeanMapper.class);

  /** 映射缓存,提高效率. */
  private static final ConcurrentHashMap<String, BeanCopier> MAPPER_CACHE =
      new ConcurrentHashMap<>();

  /**
   * 对象映射复制.
   *
   * @param source 源对象
   * @param target 目标对象的class
   * @param <O> 源对象类型
   * @param <T> 目标对象类型
   * @return 目标对象实例
   */
  public static <O, T> T mapper(O source, Class<T> target) {
    return baseMapper(source, target);
  }

  /**
   * 集合对象映射复制.
   *
   * @param list 集合对象
   * @param targetClass 目标对象的class
   * @param <T> 目标对象类型
   * @return 目标对象集合
   */
  public static <T> List<T> mapperList(List<?> list, Class<T> targetClass) {
    List<T> resultList = new LinkedList<>();

    if (CollectionUtils.isEmpty(list)) {
      return resultList;
    }

    for (Object item : list) {
      resultList.add(mapper(item, targetClass));
    }
    return resultList;
  }

  /**
   * 对象映射私有方法.
   *
   * @param source 源对象
   * @param target 目标对象的class
   * @param <O> 源对象类型
   * @param <T> 目标对象类型
   * @return 目标对象实例
   */
  private static <O, T> T baseMapper(O source, Class<T> target) {
    String baseKey = generateKey(source.getClass(), target);
    BeanCopier copier;
    if (!MAPPER_CACHE.containsKey(baseKey)) {
      copier = BeanCopier.create(source.getClass(), target, false);
      MAPPER_CACHE.put(baseKey, copier);
    } else {
      copier = MAPPER_CACHE.get(baseKey);
    }
    T instance = null;
    try {
      instance = target.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      LOG.error("mapper 创建对象异常" + e.getMessage());
    }
    copier.copy(source, instance, null);
    return instance;
  }

  /**
   * 生成缓存唯一标示.
   *
   * @param class1 数据对象的class
   * @param class2 目标对象的class
   * @return 唯一标示
   */
  private static String generateKey(Class<?> class1, Class<?> class2) {
    return class1.toString() + class2.toString();
  }
}
