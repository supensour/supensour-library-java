package com.supensour.library.data.map;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class SetValueHashMap<K, V> extends AbstractEmbeddedMap<K, Set<V>> implements SetValueMap<K, V>, Cloneable, Serializable {

  private static final long serialVersionUID = -3684709001281501910L;

  private final Supplier<? extends Set<V>> collectionSupplier;

  public SetValueHashMap(int initialCapacity, float loadFactor, Supplier<? extends Set<V>> collectionSupplier) {
    this.innerMap = new HashMap<>(initialCapacity, loadFactor);
    this.collectionSupplier = collectionSupplier;
  }

  public SetValueHashMap(int initialCapacity, Supplier<? extends Set<V>> collectionSupplier) {
    this.innerMap = new HashMap<>(initialCapacity);
    this.collectionSupplier = collectionSupplier;
  }

  public SetValueHashMap(Map<? extends K, ? extends Set<V>> m, Supplier<? extends Set<V>> collectionSupplier) {
    this.innerMap = new HashMap<>(m.size());
    this.collectionSupplier = collectionSupplier;
    this.addAll(m);
  }

  public SetValueHashMap(Map<? extends K, ? extends Set<V>> m) {
    this(m, HashSet::new);
  }

  public SetValueHashMap(Supplier<? extends Set<V>> collectionSupplier) {
    this(new HashMap<>(), collectionSupplier);
  }

  public SetValueHashMap() {
    this(new HashMap<>());
  }

  @Override
  public V getFirst(K key) {
    return Optional.ofNullable(get(key))
        .filter(values -> !values.isEmpty())
        .flatMap(values -> values.stream().findFirst())
        .orElse(null);
  }

  @Override
  public SetValueMap<K, V> add(K key, V value) {
    computeIfAbsent(key, k -> collectionSupplier.get()).add(value);
    return this;
  }

  @Override
  public SetValueMap<K, V> add(Entry<? extends K, ? extends V> entry) {
    add(entry.getKey(), entry.getValue());
    return this;
  }

  @Override
  public SetValueMap<K, V> add(Map<? extends K, ? extends V> values) {
    values.forEach(this::add);
    return this;
  }

  @Override
  public SetValueMap<K, V> addAll(K key, Set<V> values) {
    computeIfAbsent(key, k -> collectionSupplier.get()).addAll(values);
    return this;
  }

  @Override
  public SetValueMap<K, V> addAll(Entry<? extends K, ? extends Set<V>> entry) {
    addAll(entry.getKey(), entry.getValue());
    return this;
  }

  @Override
  public SetValueMap<K, V> addAll(Map<? extends K, ? extends Set<V>> values) {
    values.forEach(this::addAll);
    return this;
  }

  @Override
  public SetValueMap<K, V> set(K key, V value) {
    compute(key, (k,  old) -> collectionSupplier.get()).add(value);
    return this;
  }

  @Override
  public SetValueMap<K, V> set(Entry<? extends K, ? extends V> entry) {
    set(entry.getKey(), entry.getValue());
    return this;
  }

  @Override
  public SetValueMap<K, V> set(Map<? extends K, ? extends V> values) {
    values.forEach(this::set);
    return this;
  }

  @Override
  public SetValueMap<K, V> setAll(K key, Set<V> values) {
    compute(key, (k, old) -> collectionSupplier.get()).addAll(values);
    return this;
  }

  @Override
  public SetValueMap<K, V> setAll(Entry<? extends K, ? extends Set<V>> entry) {
    setAll(entry.getKey(), entry.getValue());
    return this;
  }

  @Override
  public SetValueMap<K, V> setAll(Map<? extends K, ? extends Set<V>> values) {
    values.forEach(this::setAll);
    return this;
  }

  @Override
  public Map<K, Set<V>> toMap() {
    Map<K, Set<V>> map = new HashMap<>();
    for (Entry<K, Set<V>> entry: entrySet()) {
      Set<V> values = Optional.ofNullable(entry.getValue())
          .map(this::cloneValues)
          .orElse(null);
      map.put(entry.getKey(), values);
    }
    return map;
  }

  @Override
  public Map<K, V> toSingleValueMap() {
    Map<K, V> map = new HashMap<>();
    for (Entry<K, Set<V>> entry: entrySet()) {
      V value = Optional.ofNullable(entry.getValue())
          .flatMap(values -> values.stream().findFirst())
          .orElse(null);
      map.put(entry.getKey(), value);
    }
    return map;
  }

  @Override
  public SetValueMap<K, V> clone() {
    return new SetValueHashMap<>(this);
  }

  private Set<V> cloneValues(Set<V> values) {
    Set<V> newValues = collectionSupplier.get();
    newValues.addAll(values);
    return newValues;
  }

}
