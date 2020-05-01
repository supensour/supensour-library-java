package com.supensour.library.model.map;

import java.util.Collection;
import java.util.Map;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public interface BaseMultiValueMap<K, V, C extends Collection<V>> extends Map<K, C> {

  V getFirst(K key);

  BaseMultiValueMap<K, V, C> add(K key, V value);

  BaseMultiValueMap<K, V, C> add(Entry<? extends K, ? extends V> entry);

  BaseMultiValueMap<K, V, C> add(Map<? extends K, ? extends V> values);

  BaseMultiValueMap<K, V, C> addAll(K key, C values);

  BaseMultiValueMap<K, V, C> addAll(Entry<? extends K, ? extends C> entry);

  BaseMultiValueMap<K, V, C> addAll(Map<? extends K, ? extends C> values);

  BaseMultiValueMap<K, V, C> set(K key, V value);

  BaseMultiValueMap<K, V, C> set(Entry<? extends K, ? extends V> entry);

  BaseMultiValueMap<K, V, C> set(Map<? extends K, ? extends V> values);

  BaseMultiValueMap<K, V, C> setAll(K key, C values);

  BaseMultiValueMap<K, V, C> setAll(Entry<? extends K, ? extends C> entry);

  BaseMultiValueMap<K, V, C> setAll(Map<? extends K, ? extends C> values);

  Map<K, C> toMap();

  Map<K, V> toSingleValueMap();

}
