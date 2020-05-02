package com.supensour.library.model.map.impl;

import com.supensour.library.libs.CollectionLib;
import com.supensour.library.model.map.MultiValueMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class MultiValueHashMap<K, V> extends AbstractEmbeddedMap<K, List<V>>
    implements MultiValueMap<K, V>, Cloneable, Serializable {

  private static final long serialVersionUID = 4762706715344820810L;

  private final Supplier<? extends List<V>> collectionSupplier;

  public MultiValueHashMap(int initialCapacity, float loadFactor, Supplier<? extends List<V>> collectionSupplier) {
    this.innerMap = new HashMap<>(initialCapacity, loadFactor);
    this.collectionSupplier = collectionSupplier;
  }

  public MultiValueHashMap(int initialCapacity, Supplier<? extends List<V>> collectionSupplier) {
    this.innerMap = new HashMap<>(initialCapacity);
    this.collectionSupplier = collectionSupplier;
  }

  public MultiValueHashMap(Map<? extends K, ? extends List<V>> m, Supplier<? extends List<V>> collectionSupplier) {
    this.innerMap = new HashMap<>(m.size());
    this.collectionSupplier = collectionSupplier;
    this.addAll(m);
  }

  public MultiValueHashMap(Map<? extends K, ? extends List<V>> m) {
    this(m, ArrayList::new);
  }

  public MultiValueHashMap(Supplier<? extends List<V>> collectionSupplier) {
    this(new HashMap<>(), collectionSupplier);
  }

  public MultiValueHashMap() {
    this(new HashMap<>());
  }

  @Override
  public V getFirst(K key) {
    return Optional.ofNullable(get(key))
        .filter(CollectionLib::isNotEmpty)
        .flatMap(values -> values.stream().findFirst())
        .orElse(null);
  }

  @Override
  public MultiValueMap<K, V> add(K key, V value) {
    computeIfAbsent(key, k -> collectionSupplier.get()).add(value);
    return this;
  }

  @Override
  public MultiValueMap<K, V> add(Entry<? extends K, ? extends V> entry) {
    add(entry.getKey(), entry.getValue());
    return this;
  }

  @Override
  public MultiValueMap<K, V> add(Map<? extends K, ? extends V> values) {
    values.forEach(this::add);
    return this;
  }

  @Override
  public MultiValueMap<K, V> addAll(K key, List<V> values) {
    computeIfAbsent(key, k -> collectionSupplier.get()).addAll(values);
    return this;
  }

  @Override
  public MultiValueMap<K, V> addAll(Entry<? extends K, ? extends List<V>> entry) {
    addAll(entry.getKey(), entry.getValue());
    return this;
  }

  @Override
  public MultiValueMap<K, V> addAll(Map<? extends K, ? extends List<V>> values) {
    values.forEach(this::addAll);
    return this;
  }

  @Override
  public MultiValueMap<K, V> set(K key, V value) {
    compute(key, (k,  old) -> collectionSupplier.get()).add(value);
    return this;
  }

  @Override
  public MultiValueMap<K, V> set(Entry<? extends K, ? extends V> entry) {
    set(entry.getKey(), entry.getValue());
    return this;
  }

  @Override
  public MultiValueMap<K, V> set(Map<? extends K, ? extends V> values) {
    values.forEach(this::set);
    return this;
  }

  @Override
  public MultiValueMap<K, V> setAll(K key, List<V> values) {
    compute(key, (k, old) -> collectionSupplier.get()).addAll(values);
    return this;
  }

  @Override
  public MultiValueMap<K, V> setAll(Entry<? extends K, ? extends List<V>> entry) {
    setAll(entry.getKey(), entry.getValue());
    return this;
  }

  @Override
  public MultiValueMap<K, V> setAll(Map<? extends K, ? extends List<V>> values) {
    putAll(values);
    return this;
  }

  @Override
  public Map<K, List<V>> toMap() {
    Map<K, List<V>> map = new HashMap<>();
    for (Entry<K, List<V>> entry: entrySet()) {
      List<V> values = Optional.ofNullable(entry.getValue())
          .map(this::cloneValues)
          .orElse(null);
      map.put(entry.getKey(), values);
    }
    return map;
  }

  @Override
  public Map<K, V> toSingleValueMap() {
    Map<K, V> map = new HashMap<>();
    for (Entry<K, List<V>> entry: entrySet()) {
      V value = Optional.ofNullable(entry.getValue())
          .flatMap(values -> values.stream().findFirst())
          .orElse(null);
      map.put(entry.getKey(), value);
    }
    return map;
  }

  @Override
  public MultiValueMap<K, V> clone() {
    return new MultiValueHashMap<>(this);
  }

  private List<V> cloneValues(List<V> values) {
    List<V> newValues = collectionSupplier.get();
    newValues.addAll(values);
    return newValues;
  }

}
