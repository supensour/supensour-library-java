package com.supensour.library.libs;

import com.supensour.library.model.group.Pair;
import com.supensour.library.model.map.impl.MultiValueHashMap;
import com.supensour.library.model.map.MultiValueMap;
import org.junit.Test;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Supplier;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class CollectionLibTest {

  @Test
  public void isNotEmptyMap() {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(1, 1);
    assertTrue(CollectionLib.isNotEmpty(map));
  }

  @Test
  public void isNotEmptyMultiValueMap() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.add(1, 1);
    assertTrue(CollectionLib.isNotEmpty(map));
  }

  @Test
  public void isNotEmptyCollection() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    assertTrue(CollectionLib.isNotEmpty(list));
  }

  @Test
  public void isNotEmptyIterable() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    assertTrue(CollectionLib.isNotEmpty((Iterable<Integer>)list));
  }

  @Test
  public void isNotEmptyArray() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    assertTrue(CollectionLib.isNotEmpty(list.toArray()));
  }

  @Test
  public void isEmptyMap() {
    Map<Integer, Integer> map = new HashMap<>();
    assertTrue(CollectionLib.isEmpty(map));
    map.put(1, 1);
    assertFalse(CollectionLib.isEmpty(map));
    map = null;
    assertTrue(CollectionLib.isEmpty(map));
  }

  @Test
  public void isEmptyMultiValueMap() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    assertTrue(CollectionLib.isEmpty(map));
    map.add(1, 1);
    assertFalse(CollectionLib.isEmpty(map));
    map = null;
    assertTrue(CollectionLib.isEmpty(map));
  }

  @Test
  public void isEmptyCollection() {
    List<Integer> list = new ArrayList<>();
    assertTrue(CollectionLib.isEmpty(list));
    list.add(1);
    assertFalse(CollectionLib.isEmpty(list));
    list = null;
    assertTrue(CollectionLib.isEmpty(list));
  }

  @Test
  public void isEmptyIterable() {
    List<Integer> list = new ArrayList<>();
    assertTrue(CollectionLib.isEmpty((Iterable<Integer>) list));
    list.add(1);
    assertFalse(CollectionLib.isEmpty((Iterable<Integer>) list));
    list = null;
    assertTrue(CollectionLib.isEmpty((Iterable<Integer>) list));
  }

  @Test
  public void isEmptyArray() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    assertFalse(CollectionLib.isEmpty(list.toArray()));
    assertTrue(CollectionLib.isEmpty(new String[] {}));
    assertTrue(CollectionLib.isEmpty((Integer[]) null));
  }

  @Test
  public void toList() {
    List<String> values = CollectionLib.toList("A");
    assertEquals(1, values.size());
    assertEquals("A", values.get(0));

    values = CollectionLib.toList("A", "B", "C");
    assertEquals(3, values.size());
    assertEquals("A", values.get(0));
    assertEquals("B", values.get(1));
    assertEquals("C", values.get(2));
  }

  @Test
  public void toSet() {
    Set<String> values = CollectionLib.toSet("A");
    assertEquals(1, values.size());
    assertEquals("A", values.iterator().next());

    values = CollectionLib.toSet("A", "B", "A");
    assertEquals(2, values.size());
    Iterator<String> iterator = values.iterator();
    assertEquals("A", iterator.next());
    assertEquals("B", iterator.next());
  }

  @Test
  public void addToMapWithPair() {
    Map<Integer, Integer> map = CollectionLib.addToMap((Map<Integer, Integer>) null, Pair.of(1, 2));
    assertEquals(1, map.size());
    assertEquals(2, map.get(1).intValue());
  }

  @Test
  public void addToMapWithEntry() {
    Map<Integer, Integer> map = CollectionLib.addToMap((Map<Integer, Integer>) null, new SimpleEntry<>(1, 2));
    assertEquals(1, map.size());
    assertEquals(2, map.get(1).intValue());
  }

  @Test
  public void addToMapWithKeyValue() {
    Map<String, Integer> map = null;
    CollectionLib.addToMap(map,"1", 1);
    assertNull(map);

    map = CollectionLib.addToMap(map, "1", 1);
    assertTrue(map.containsKey("1"));
    assertEquals(1, map.size());
    assertEquals(1, map.get("1").intValue());
  }

  @Test
  public void addToMapWithPairToSupplier() {
    Map<Integer, Integer> map = CollectionLib.addToMap(HashMap::new, Pair.of(1, 2));
    assertEquals(1, map.size());
    assertEquals(2, map.get(1).intValue());
  }

  @Test
  public void addToMapWithEntryToSupplier() {
    Map<Integer, Integer> map = CollectionLib.addToMap(HashMap::new, new SimpleEntry<>(1, 2));
    assertEquals(1, map.size());
    assertEquals(2, map.get(1).intValue());
  }

  @Test
  public void addToMapWithKeyValueToSupplier() {
    Map<String, Integer> map = CollectionLib.addToMap(HashMap::new, "1", 1);
    assertTrue(map.containsKey("1"));
    assertEquals(1, map.size());
    assertEquals(1, map.get("1").intValue());
  }

  @Test
  public void addToMultiValueMapWithPair() {
    Pair<Integer, Integer> pair = Pair.of(1, 2);
    Map<Integer, List<Integer>> map = CollectionLib.addToMultiValueMap((Map<Integer, List<Integer>>) null, pair);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addToMultiValueMapWithEntry() {
    Entry<Integer, Integer> entry = new SimpleEntry<>(1, 2);
    Map<Integer, List<Integer>> map = CollectionLib.addToMultiValueMap((Map<Integer, List<Integer>>) null, entry);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addToMultiValueMapWithSingleValueMap() {
    Map<Integer, Integer> other = new HashMap<>();
    other.put(1, 2);
    Map<Integer, List<Integer>> map = CollectionLib.addToMultiValueMap((Map<Integer, List<Integer>>) null, other);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addToMultiValueMapWithKeyValue() {
    Map<Integer, List<Integer>> map = CollectionLib.addToMultiValueMap((Map<Integer, List<Integer>>) null, 1, 2);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addToMultiValueMapWithPairToSupplier() {
    Pair<Integer, Integer> pair = Pair.of(1, 2);
    Map<Integer, List<Integer>> map = CollectionLib.addToMultiValueMap(HashMap::new, pair);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addToMultiValueMapWithEntryToSupplier() {
    Entry<Integer, Integer> entry = new SimpleEntry<>(1, 2);
    Map<Integer, List<Integer>> map = CollectionLib.addToMultiValueMap(HashMap::new, entry);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addToMultiValueMapWithSingleValueMapToSupplier() {
    Map<Integer, Integer> other = new HashMap<>();
    other.put(1, 2);
    Map<Integer, List<Integer>> map = CollectionLib.addToMultiValueMap(HashMap::new, other);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addToMultiValueMapWithKeyValueToSupplier() {
    Map<Integer, List<Integer>> map = CollectionLib.addToMultiValueMap(HashMap::new, 1, 2);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addAllToMultiValueMapWithPair() {
    Pair<Integer, Set<Integer>> pair = Pair.of(1, CollectionLib.toSet(2));
    Map<Integer, List<Integer>> map = CollectionLib.addAllToMultiValueMap((Map<Integer, List<Integer>>) null, pair);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addAllToMultiValueMapWithEntry() {
    Entry<Integer, Set<Integer>> entry = new SimpleEntry<>(1, CollectionLib.toSet(2));
    Map<Integer, List<Integer>> map = CollectionLib.addAllToMultiValueMap((Map<Integer, List<Integer>>) null, entry);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addAllToMultiValueMapWithMultiValueMap() {
    Map<Integer, Set<Integer>> other = new HashMap<>();
    other.put(1, CollectionLib.toSet(2));
    Map<Integer, List<Integer>> map = CollectionLib.addAllToMultiValueMap((Map<Integer, List<Integer>>) null, other);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addAllToMultiValueMapWithKeyValue() {
    Set<Integer> value = new HashSet<>();
    value.add(2);
    Map<Integer, List<Integer>> map = CollectionLib.addAllToMultiValueMap((Map<Integer, List<Integer>>) null, 1, value);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addAllToMultiValueMapWithPairToSupplier() {
    Pair<Integer, Set<Integer>> pair = Pair.of(1, CollectionLib.toSet(2));
    Map<Integer, List<Integer>> map = CollectionLib.addAllToMultiValueMap(HashMap::new, pair);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addAllToMultiValueMapWithEntryToSupplier() {
    Entry<Integer, Set<Integer>> entry = new SimpleEntry<>(1, CollectionLib.toSet(2));
    Map<Integer, List<Integer>> map = CollectionLib.addAllToMultiValueMap(HashMap::new, entry);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addAllToMultiValueMapWithMultiValueMapToSupplier() {
    Map<Integer, Set<Integer>> other = new HashMap<>();
    other.put(1, CollectionLib.toSet(2));
    Map<Integer, List<Integer>> map = CollectionLib.addAllToMultiValueMap(HashMap::new, other);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void addAllToMultiValueMapWithKeyValueToSupplier() {
    Set<Integer> value = new HashSet<>();
    value.add(2);
    Map<Integer, List<Integer>> map = CollectionLib.addAllToMultiValueMap(HashMap::new, 1, value);
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toList(2), map.get(1));
  }

  @Test
  public void join() {
    List<String> values1 = CollectionLib.toList("1", "2");
    List<String> values2 = CollectionLib.toList("3", "4");
    List<String> result = CollectionLib.join(values1, values2);

    assertEquals(4, result.size());
    assertEquals("1", result.get(0));
    assertEquals("2", result.get(1));
    assertEquals("3", result.get(2));
    assertEquals("4", result.get(3));

    assertEquals(4, values1.size());
    assertEquals("1", values1.get(0));
    assertEquals("2", values1.get(1));
    assertEquals("3", values1.get(2));
    assertEquals("4", values1.get(3));

    assertEquals(2, values2.size());
    assertEquals("3", values2.get(0));
    assertEquals("4", values2.get(1));
  }

  @Test
  public void joinNullTarget() {
    List<String> values1 = CollectionLib.toList("1", "2");
    List<String> values2 = CollectionLib.toList("3", "4");

    assertNull(CollectionLib.join((List<String>) null, values1, values2));

    assertEquals(2, values1.size());
    assertEquals("1", values1.get(0));
    assertEquals("2", values1.get(1));

    assertEquals(2, values2.size());
    assertEquals("3", values2.get(0));
    assertEquals("4", values2.get(1));
  }

  @Test
  public void joinToSupplier() {
    List<String> values1 = CollectionLib.toList("1", "2");
    List<String> values2 = CollectionLib.toList("3", "4");
    List<String> result = CollectionLib.join((Supplier<List<String>>) ArrayList::new, values1, values2);

    assertEquals(4, result.size());
    assertEquals("1", result.get(0));
    assertEquals("2", result.get(1));
    assertEquals("3", result.get(2));
    assertEquals("4", result.get(3));

    assertEquals(2, values1.size());
    assertEquals("1", values1.get(0));
    assertEquals("2", values1.get(1));

    assertEquals(2, values2.size());
    assertEquals("3", values2.get(0));
    assertEquals("4", values2.get(1));
  }

  @Test
  public void joinMaps() {
    Map<Integer, Integer> values1 = CollectionLib.addToMap(HashMap::new, 0, 1);
    Map<Integer, Integer> values2 = CollectionLib.addToMap(HashMap::new, 1, 2);
    Map<Integer, Integer> values3 = CollectionLib.addToMap(HashMap::new, 1, 3);
    Map<Integer, Integer> values4 = CollectionLib.addToMap(HashMap::new, 2, 4);
    Map<Integer, Integer> result = CollectionLib.joinMaps(values1, values2, values3, values4);

    assertEquals(3, result.size());
    assertEquals(1, result.get(0).intValue());
    assertEquals(3, result.get(1).intValue());
    assertEquals(4, result.get(2).intValue());

    assertEquals(3, values1.size());
    assertEquals(1, values1.get(0).intValue());
    assertEquals(3, values1.get(1).intValue());
    assertEquals(4, values1.get(2).intValue());

    assertEquals(1, values2.size());
    assertEquals(2, values2.get(1).intValue());

    assertEquals(1, values3.size());
    assertEquals(3, values3.get(1).intValue());

    assertEquals(1, values4.size());
    assertEquals(4, values4.get(2).intValue());
  }

  @Test
  public void joinMapsNullTarget() {
    Map<Integer, Integer> values1 = CollectionLib.addToMap(HashMap::new, 0, 1);
    Map<Integer, Integer> values2 = CollectionLib.addToMap(HashMap::new, 1, 2);

    assertNull(CollectionLib.joinMaps((Map<Integer, Integer>) null, values1, values2));

    assertEquals(1, values1.size());
    assertEquals(1, values1.get(0).intValue());

    assertEquals(1, values2.size());
    assertEquals(2, values2.get(1).intValue());
  }

  @Test
  public void joinMapsToSupplier() {
    Map<Integer, Integer> values1 = CollectionLib.addToMap(HashMap::new, 0, 1);
    Map<Integer, Integer> values2 = CollectionLib.addToMap(HashMap::new, 1, 2);
    Map<Integer, Integer> values3 = CollectionLib.addToMap(HashMap::new, 1, 3);
    Map<Integer, Integer> values4 = CollectionLib.addToMap(HashMap::new, 2, 4);
    Map<Integer, Integer> result = CollectionLib.joinMaps((Supplier<Map<Integer, Integer>>) HashMap::new, values1, values2, values3, values4);

    assertEquals(3, result.size());
    assertEquals(1, result.get(0).intValue());
    assertEquals(3, result.get(1).intValue());
    assertEquals(4, result.get(2).intValue());

    assertEquals(1, values1.size());
    assertEquals(1, values1.get(0).intValue());

    assertEquals(1, values2.size());
    assertEquals(2, values2.get(1).intValue());

    assertEquals(1, values3.size());
    assertEquals(3, values3.get(1).intValue());

    assertEquals(1, values4.size());
    assertEquals(4, values4.get(2).intValue());
  }

  @Test
  public void isUnmodifiable_set() {
    Set<Object> set = new HashSet<>();
    assertTrue(CollectionLib.isUnmodifiable(Collections.unmodifiableSet(set)));
    assertFalse(CollectionLib.isUnmodifiable(set));
  }

  @Test
  public void isUnmodifiable_list() {
    List<Object> list = new ArrayList<>();
    assertTrue(CollectionLib.isUnmodifiable(Collections.unmodifiableList(list)));
    assertFalse(CollectionLib.isUnmodifiable(list));
  }

  @Test
  public void isUnmodifiable_map() {
    Map<Object, Object> map = new HashMap<>();
    assertTrue(CollectionLib.isUnmodifiable(Collections.unmodifiableMap(map)));
    assertFalse(CollectionLib.isUnmodifiable(map));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void unmodifiable_set() {
    Set<Object> set = CollectionLib.unmodifiable(new HashSet<>());
    assertTrue(CollectionLib.isUnmodifiable(set));
    set.add(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void unmodifiable_list() {
    List<Object> list = CollectionLib.unmodifiable(new ArrayList<>());
    assertTrue(CollectionLib.isUnmodifiable(list));
    list.add(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void unmodifiable_map() {
    Map<Object, Object> map = CollectionLib.unmodifiable(new HashMap<>());
    assertTrue(CollectionLib.isUnmodifiable(map));
    map.put(null, null);
  }

}
