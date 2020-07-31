package com.ucucs.wxwork.core.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * coding.
 *
 * @author ucucs.
 */
public class MapBuilder<K, V> {

  private final Map<K, V> map;

  /** Create a HashMap builder. */
  public MapBuilder() {
    map = new HashMap<>();
  }

  /**
   * Create a HashMap builder.
   *
   * @param initialCapacity 容量
   */
  public MapBuilder(int initialCapacity) {
    map = new HashMap<>(initialCapacity);
  }

  /**
   * Create a Map builder.
   *
   * @param mapFactory Map键值供应者
   */
  public MapBuilder(Supplier<Map<K, V>> mapFactory) {
    map = mapFactory.get();
  }

  public MapBuilder<K, V> put(K key, V value) {
    map.put(key, value);
    return this;
  }

  public Map<K, V> build() {
    return map;
  }

  /**
   * Returns an unmodifiable Map. Strictly speaking, the Map is not immutable because any code with
   * a reference to the builder could mutate it.
   *
   * @return 不可变对象
   */
  public Map<K, V> buildUnmodifiable() {
    return Collections.unmodifiableMap(map);
  }
}
