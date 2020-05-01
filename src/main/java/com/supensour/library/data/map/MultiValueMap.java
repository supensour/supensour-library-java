package com.supensour.library.data.map;

import java.util.List;
import java.util.Map;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public interface MultiValueMap<K, V> extends BaseMultiValueMap<K, V, List<V>> {

  @Override
  MultiValueMap<K, V> add(K key, V value);

  @Override
  MultiValueMap<K, V> add(Entry<? extends K, ? extends V> entry);

  @Override
  MultiValueMap<K, V> add(Map<? extends K, ? extends V> values);

  @Override
  MultiValueMap<K, V> addAll(K key, List<V> values);

  @Override
  MultiValueMap<K, V> addAll(Entry<? extends K, ? extends List<V>> entry);

  @Override
  MultiValueMap<K, V> addAll(Map<? extends K, ? extends List<V>> values);

  @Override
  MultiValueMap<K, V> set(K key, V value);

  @Override
  MultiValueMap<K, V> set(Entry<? extends K, ? extends V> entry);

  @Override
  MultiValueMap<K, V> set(Map<? extends K, ? extends V> values);

  @Override
  MultiValueMap<K, V> setAll(K key, List<V> values);

  @Override
  MultiValueMap<K, V> setAll(Entry<? extends K, ? extends List<V>> entry);

  @Override
  MultiValueMap<K, V> setAll(Map<? extends K, ? extends List<V>> values);

}
