package com.supensour.library.libs;

import com.supensour.library.data.Pair;
import com.supensour.library.data.map.BaseMultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author Suprayan Yapura
 * @since April 19, 2020
 */
public class CollectionLib {

  private final static Set<?> EMPTY_UNMODIFIABLE_SET = Collections.unmodifiableSet(new HashSet<>());
  private final static List<?> EMPTY_UNMODIFIABLE_LIST = Collections.unmodifiableList(new ArrayList<>());
  private final static Map<?, ?> EMPTY_UNMODIFIABLE_MAP = Collections.unmodifiableMap(new HashMap<>());

  /**
   * To check if whether given map is not empty
   *
   * @param map   the map to be checked
   * @return true only if the map is not empty
   */
  public static boolean isNotEmpty(Map<?, ?> map) {
    return Optional.ofNullable(map)
        .filter(m -> !m.isEmpty())
        .isPresent();
  }

  /**
   * To check if whether given multi value map is not empty
   *
   * @param multiValueMap   the multi value map to be checked
   * @return true only if the multi value map is not empty
   */
  public static boolean isNotEmpty(BaseMultiValueMap<?, ?, ?> multiValueMap) {
    return Optional.ofNullable(multiValueMap)
        .filter(m -> !m.isEmpty())
        .isPresent();
  }

  /**
   * To check if whether given collection is not empty
   *
   * @param collection  the collection to be checked
   * @return true only if the map is not empty
   */
  public static boolean isNotEmpty(Collection<?> collection) {
    return Optional.ofNullable(collection)
        .filter(c -> !c.isEmpty())
        .isPresent();
  }

  /**
   * To check if whether given map is null or empty
   *
   * @param map   the map to be checked
   * @return true only if the map is null or empty
   */
  public static boolean isEmpty(Map<?, ?> map) {
    return !isNotEmpty(map);
  }

  /**
   * To check if whether given multi value map is null or empty
   *
   * @param multiValueMap   the multi value map to be checked
   * @return true only if the multi value map is null or empty
   */
  public static boolean isEmpty(BaseMultiValueMap<?, ?, ?> multiValueMap) {
    return !isNotEmpty(multiValueMap);
  }

  /**
   * To check if whether given collection is null or empty
   *
   * @param collection  the collection to be checked
   * @return true only if the map is null or empty
   */
  public static boolean isEmpty(Collection<?> collection) {
    return !isNotEmpty(collection);
  }

  /**
   * Collect values to {@link List}.
   *
   * @param values  values to be collected to list
   *
   * @return a list contains collected values
   */
  @SafeVarargs
  public static <T> List<T> toList(T... values) {
    return new ArrayList<>(Arrays.asList(values));
  }

  /**
   * Collect values to {@link Set}.
   *
   * @param values  values to be collected to set
   *
   * @return a set contains unique collected values
   */
  @SafeVarargs
  public static <T> Set<T> toSet(T... values) {
    return new HashSet<>(Arrays.asList(values));
  }

  /**
   * Add value to map.
   *
   * This is an overloaded method that receive {@link Pair}.
   * The key should be stored as {@link Pair#setFirst(K)}.
   * The value should be stored as {@link Pair#setSecond(V)}.
   *
   * @param map     the map to be added to
   * @param kvPair  a pair contains of key and value to be added
   *
   * @throws NullPointerException if kvPair or the key is null
   * @return map
   */
  public static <K, V> Map<K, V> addToMap(Map<K, V> map, Pair<K, V> kvPair) {
    Objects.requireNonNull(kvPair, "key-value pair must not be null");
    return addToMap(map, kvPair.getFirst(), kvPair.getSecond());
  }

  /**
   * Add value to map.
   *
   * This is an overloaded method that receive {@link Entry}.
   *
   * @param map     the map to be added to
   * @param entry   a map entry
   *
   * @throws NullPointerException if entry or the key is null
   * @return map
   */
  public static <K, V> Map<K, V> addToMap(Map<K, V> map, Entry<K, V> entry) {
    Objects.requireNonNull(entry, "entry must not be null");
    return addToMap(map, entry.getKey(), entry.getValue());
  }

  /**
   * Add value to map.
   *
   * @param map     the map to be added to
   * @param key     the key where the value to be added on
   * @param value   the value to be added
   *
   * @throws NullPointerException if the key is null
   * @return map
   */
  public static <K, V> Map<K, V> addToMap(Map<K, V> map, K key, V value) {
    Objects.requireNonNull(key, "Key must not be null");
    map = Optional.ofNullable(map).orElseGet(HashMap::new);
    map.put(key, value);
    return map;
  }

  /**
   * Add value to map.
   *
   * This is an overloaded method that receive {@link Pair}.
   * The key should be stored as {@link Pair#setFirst(K)}.
   * The value should be stored as {@link Pair#setSecond(V)}.
   *
   * @param mapSupplier   supplier of the map to be added to
   * @param kvPair        a pair contains of key and value to be added
   *
   * @throws NullPointerException if mapSupplier, kvPair or the key is null
   * @return map
   */
  public static <K, V> Map<K, V> addToMap(Supplier<Map<K, V>> mapSupplier, Pair<K, V> kvPair) {
    Objects.requireNonNull(mapSupplier, "map supplier must not be null");
    return addToMap(mapSupplier.get(), kvPair);
  }

  /**
   * Add value to map.
   *
   * This is an overloaded method that receive {@link Entry}.
   *
   * @param mapSupplier   supplier of the map to be added to
   * @param entry         a map entry
   *
   * @throws NullPointerException if mapSupplier, entry or the key is null
   * @return map
   */
  public static <K, V> Map<K, V> addToMap(Supplier<Map<K, V>> mapSupplier, Entry<K, V> entry) {
    Objects.requireNonNull(mapSupplier, "map supplier must not be null");
    return addToMap(mapSupplier.get(), entry);
  }

  /**
   * Add value to map.
   *
   * @param mapSupplier   supplier of the map to be added to
   * @param key           the key where the value to be added on
   * @param value         the value to be added
   *
   * @throws NullPointerException if mapSupplier or the key is null
   * @return map
   */
  public static <K, V> Map<K, V> addToMap(Supplier<Map<K, V>> mapSupplier, K key, V value) {
    Objects.requireNonNull(mapSupplier, "map supplier must not be null");
    return addToMap(mapSupplier.get(), key, value);
  }

  /**
   * Join all collection sources to the target
   * Make sure that the target is not null or the result will be null.
   *
   * @param target    the target variable where the sources to be appended to
   * @param sources   list of collections to be appended to the target
   * @return the target
   */
  @SafeVarargs
  public static <T, C extends Collection<T>> C join(C target, Collection<T>... sources) {
    if (target != null) {
      Arrays.stream(sources)
          .filter(Objects::nonNull)
          .forEach(target::addAll);
    }
    return target;
  }

  /**
   * Join all collection sources to the target
   *
   * @param targetSupplier  the target supplier where the sources to be appended to
   * @param sources         list of collections to be appended to the target
   *
   * @throws NullPointerException if targetSupplier is null
   * @return the target
   */
  @SafeVarargs
  public static <T, C extends Collection<T>> C join(Supplier<C> targetSupplier, Collection<T>... sources) {
    Objects.requireNonNull(targetSupplier, "Target supplier must not be null");
    return join(targetSupplier.get(), sources);
  }

  /**
   * Join all map sources to the target
   * Make sure that the target is not null or the result will be null.
   *
   * @param target    the target variable where the sources to be appended to
   * @param sources   list of maps to be appended to the target
   * @return the target
   */
  @SafeVarargs
  public static <K, V, M extends Map<K, V>> M joinMaps(M target, Map<K, V>... sources) {
    if (target != null) {
      Arrays.stream(sources)
          .filter(Objects::nonNull)
          .forEach(target::putAll);
    }
    return target;
  }

  /**
   * Join all map sources to the target
   *
   * @param targetSupplier  the target supplier where the sources to be appended to
   * @param sources         list of maps to be appended to the target
   *
   * @throws NullPointerException if targetSupplier is null
   * @return the target
   */
  @SafeVarargs
  public static <K, V, M extends Map<K, V>> M joinMaps(Supplier<M> targetSupplier, Map<K, V>... sources) {
    Objects.requireNonNull(targetSupplier, "Target supplier must not be null");
    return joinMaps(targetSupplier.get(), sources);
  }

  public static <T> boolean isUnmodifiable(Set<T> set) {
    return EMPTY_UNMODIFIABLE_SET.getClass().isInstance(set);
  }

  public static <T> boolean isUnmodifiable(List<T> list) {
    return EMPTY_UNMODIFIABLE_LIST.getClass().isInstance(list);
  }

  public static <K, V> boolean isUnmodifiable(Map<K, V> map) {
    return EMPTY_UNMODIFIABLE_MAP.getClass().isInstance(map);
  }

  public static <T> Set<T> unmodifiable(Set<T> set) {
    return Collections.unmodifiableSet(set);
  }

  public static <T> List<T> unmodifiable(List<T> list) {
    return Collections.unmodifiableList(list);
  }

  public static <K, V> Map<K, V> unmodifiable(Map<K, V> map) {
    return Collections.unmodifiableMap(map);
  }

}
