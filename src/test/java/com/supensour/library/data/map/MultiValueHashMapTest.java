package com.supensour.library.data.map;

import com.supensour.library.libs.CollectionLib;
import org.junit.Test;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since April 20, 2020
 */
public class MultiValueHashMapTest {

  @Test
  public void constructor() {
    assertNewEmptyMap(new MultiValueHashMap<>(1, 0.5f, ArrayList::new));
    assertNewEmptyMap(new MultiValueHashMap<>(1, ArrayList::new));
    assertNewEmptyMap(new MultiValueHashMap<>(new HashMap<>(), ArrayList::new));
    assertNewEmptyMap(new MultiValueHashMap<>(new HashMap<>()));
    assertNewEmptyMap(new MultiValueHashMap<>(ArrayList::new));
    assertNewEmptyMap(new MultiValueHashMap<>());
  }

  @Test
  public void getFirst() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.add(1, 10);
    assertNull(map.getFirst(0));
    assertEquals(10, map.getFirst(1).intValue());
  }

  @Test
  public void addWithKeyValue() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.add(1, 10);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(1, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
    assertTrue(map.containsKey(1));
    assertTrue(map.containsValue(CollectionLib.toList(10)));
  }

  @Test
  public void addWithEntry() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.add(new SimpleEntry<>(1, 10));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(1, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
    assertTrue(map.containsKey(1));
    assertTrue(map.containsValue(CollectionLib.toList(10)));
  }

  @Test
  public void addWithSingleValueMap() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    Map<Integer, Integer> map2 = new HashMap<>();
    map2.put(1, 10);
    map.add(map2);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(1, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
    assertTrue(map.containsKey(1));
    assertTrue(map.containsValue(CollectionLib.toList(10)));
  }

  @Test
  public void addAllWithKeyValues() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10, 20));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(2, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
    assertEquals(20, map.get(1).get(1).intValue());
  }

  @Test
  public void addAllWithEntry() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(new SimpleEntry<>(1, CollectionLib.toList(10, 20)));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(2, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
    assertEquals(20, map.get(1).get(1).intValue());
  }

  @Test
  public void addAllWithMultiValueMap() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    MultiValueMap<Integer, Integer> map2 = new MultiValueHashMap<>();
    map2.add(1, 10);
    map2.add(1, 20);
    map.addAll(map2);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(2, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
    assertEquals(20, map.get(1).get(1).intValue());
  }

  @Test
  public void setWithKeyValue() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.set(1, 1);
    map.set(1, 10);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(1, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
  }

  @Test
  public void setWithEntry() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.set(1, 1);
    map.set(new SimpleEntry<>(1, 10));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(1, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
  }

  @Test
  public void setWithSingleValueMap() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.set(1, 1);
    Map<Integer, Integer> map2 = new HashMap<>();
    map2.put(1, 10);
    map.set(map2);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(1, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
  }

  @Test
  public void setAllWithKeyValues() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10, 20, 30));
    map.setAll(1, CollectionLib.toList(10, 20));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(2, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
    assertEquals(20, map.get(1).get(1).intValue());
  }

  @Test
  public void setAllWithEntry() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10, 20, 30));
    map.setAll(new SimpleEntry<>(1, CollectionLib.toList(10, 20)));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(2, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
    assertEquals(20, map.get(1).get(1).intValue());
  }

  @Test
  public void setAllWithMultiValueMap() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10, 20, 30));
    map.addAll(2, CollectionLib.toList(11, 12, 13));
    MultiValueMap<Integer, Integer> map2 = new MultiValueHashMap<>();
    map2.add(1, 10);
    map2.add(1, 20);
    map.setAll(map2);

    assertNull(map.getFirst(0));
    assertEquals(2, map.size());

    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(2, map.get(1).size());
    assertEquals(10, map.get(1).get(0).intValue());
    assertEquals(20, map.get(1).get(1).intValue());

    assertEquals(11, map.getFirst(2).intValue());
    assertEquals(3, map.get(2).size());
    assertEquals(11, map.get(2).get(0).intValue());
    assertEquals(12, map.get(2).get(1).intValue());
    assertEquals(13, map.get(2).get(2).intValue());
  }


  @Test
  public void toMap() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(0, CollectionLib.toList(1, 2, 3));
    map.addAll(1, CollectionLib.toList(4, 5, 6));

    Map<Integer, List<Integer>> map2 = map.toMap();
    assertEquals(2, map2.size());
    assertEquals(3, map2.get(0).size());
    assertEquals(1, map2.get(0).get(0).intValue());
    assertEquals(2, map2.get(0).get(1).intValue());
    assertEquals(3, map2.get(0).get(2).intValue());
    assertEquals(3, map2.get(1).size());
    assertEquals(4, map2.get(1).get(0).intValue());
    assertEquals(5, map2.get(1).get(1).intValue());
    assertEquals(6, map2.get(1).get(2).intValue());

    assertEquals(map, map2);
    assertEquals(map2, map);
    assertEquals(map, map);
    assertEquals(map2, map2);
  }

  @Test
  public void toSingleValueMap() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(0, CollectionLib.toList(1, 2, 3));
    map.addAll(1, CollectionLib.toList(4, 5, 6));

    Map<Integer, Integer> map2 = map.toSingleValueMap();
    assertEquals(2, map2.size());
    assertEquals(1, map2.get(0).intValue());
    assertEquals(4, map2.get(1).intValue());
  }

  @Test
  public void testClone() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(0, CollectionLib.toList(1, 2, 3));
    map.addAll(1, CollectionLib.toList(4, 5, 6));

    Map<Integer, List<Integer>> map2 = ((MultiValueHashMap<Integer, Integer>) map).clone();
    assertEquals(2, map2.size());
    assertEquals(3, map2.get(0).size());
    assertEquals(1, map2.get(0).get(0).intValue());
    assertEquals(2, map2.get(0).get(1).intValue());
    assertEquals(3, map2.get(0).get(2).intValue());
    assertEquals(3, map2.get(1).size());
    assertEquals(4, map2.get(1).get(0).intValue());
    assertEquals(5, map2.get(1).get(1).intValue());
    assertEquals(6, map2.get(1).get(2).intValue());

    assertEquals(map, map2);
    assertEquals(map2, map);
    assertEquals(map, map);
    assertEquals(map2, map2);
    assertTrue(map2 instanceof MultiValueMap);
    assertTrue(map2 instanceof MultiValueHashMap);
  }

  @Test
  public void clear() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10, 20, 30));
    map.addAll(2, CollectionLib.toList(11, 12, 13));
    map.clear();
    assertEquals(0, map.size());
  }

  @Test
  public void keySet() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10, 20, 30));
    map.addAll(2, CollectionLib.toList(11, 12, 13));

    List<Integer> keys = new ArrayList<>(map.keySet());
    assertEquals(2, keys.size());

    assertEquals(1, keys.get(0).intValue());
    assertEquals(2, keys.get(1).intValue());
  }

  @Test
  public void values() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10, 20, 30));
    map.addAll(2, CollectionLib.toList(11, 12, 13));

    List<List<Integer>> values = new ArrayList<>(map.values());
    assertEquals(2, values.size());

    assertEquals(3, values.get(0).size());
    assertEquals(10, values.get(0).get(0).intValue());
    assertEquals(20, values.get(0).get(1).intValue());
    assertEquals(30, values.get(0).get(2).intValue());

    assertEquals(3, values.get(1).size());
    assertEquals(11, values.get(1).get(0).intValue());
    assertEquals(12, values.get(1).get(1).intValue());
    assertEquals(13, values.get(1).get(2).intValue());
  }

  @Test
  public void entrySet() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10, 20, 30));
    map.addAll(2, CollectionLib.toList(11, 12, 13));

    List<Entry<Integer, List<Integer>>> entries = new ArrayList<>(map.entrySet());
    assertEquals(2, entries.size());

    assertEquals(1, entries.get(0).getKey().intValue());
    assertEquals(3, entries.get(0).getValue().size());
    assertEquals(10, entries.get(0).getValue().get(0).intValue());
    assertEquals(20, entries.get(0).getValue().get(1).intValue());
    assertEquals(30, entries.get(0).getValue().get(2).intValue());

    assertEquals(2, entries.get(1).getKey().intValue());
    assertEquals(3, entries.get(1).getValue().size());
    assertEquals(11, entries.get(1).getValue().get(0).intValue());
    assertEquals(12, entries.get(1).getValue().get(1).intValue());
    assertEquals(13, entries.get(1).getValue().get(2).intValue());
  }

  @Test
  public void testHashCode() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10));
    map.addAll(2, CollectionLib.toList(11));
    assertEquals(80, map.hashCode());
  }

  @Test
  public void testToString() {
    MultiValueMap<Integer, Integer> map = new MultiValueHashMap<>();
    map.addAll(1, CollectionLib.toList(10, 20));
    map.addAll(2, CollectionLib.toList(11, 12));
    map.addAll(3, CollectionLib.toList(11, 12));
    map.remove(3);
    assertEquals("{1=[10, 20], 2=[11, 12]}", map.toString());
  }

  private void assertNewEmptyMap(MultiValueMap<?, ?> map) {
    assertNotNull(map);
    assertEquals(0, map.size());
    assertTrue(map.isEmpty());
    assertEquals(0, map.entrySet().size());
  }

}
