package com.supensour.library.model.map;

import java.util.Map;
import java.util.Set;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public interface SetValueMap<K, V> extends BaseMultiValueMap<K, V, Set<V>> {

  @Override
  SetValueMap<K, V> add(K key, V value);

  @Override
  SetValueMap<K, V> add(Entry<? extends K, ? extends V> entry);

  @Override
  SetValueMap<K, V> add(Map<? extends K, ? extends V> values);

  @Override
  SetValueMap<K, V> addAll(K key, Set<V> values);

  @Override
  SetValueMap<K, V> addAll(Entry<? extends K, ? extends Set<V>> entry);

  @Override
  SetValueMap<K, V> addAll(Map<? extends K, ? extends Set<V>> values);

  @Override
  SetValueMap<K, V> set(K key, V value);

  @Override
  SetValueMap<K, V> set(Entry<? extends K, ? extends V> entry);

  @Override
  SetValueMap<K, V> set(Map<? extends K, ? extends V> values);

  @Override
  SetValueMap<K, V> setAll(K key, Set<V> values);

  @Override
  SetValueMap<K, V> setAll(Entry<? extends K, ? extends Set<V>> entry);

  @Override
  SetValueMap<K, V> setAll(Map<? extends K, ? extends Set<V>> values);

}
