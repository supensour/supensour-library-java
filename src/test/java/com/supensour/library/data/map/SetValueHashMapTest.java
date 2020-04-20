package com.supensour.library.data.map;

import com.supensour.library.utils.CollectionLib;
import org.junit.Test;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Suprayan Yapura
 * @since April 20, 2020
 */
public class SetValueHashMapTest {

  @Test
  public void constructor() {
    assertNewEmptyMap(new SetValueHashMap<>(1, 0.5f, HashSet::new));
    assertNewEmptyMap(new SetValueHashMap<>(1, HashSet::new));
    assertNewEmptyMap(new SetValueHashMap<>(new HashMap<>(), HashSet::new));
    assertNewEmptyMap(new SetValueHashMap<>(new HashMap<>()));
    assertNewEmptyMap(new SetValueHashMap<>(HashSet::new));
    assertNewEmptyMap(new SetValueHashMap<>());
  }

  @Test
  public void getFirst() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.add(1, 10);
    assertNull(map.getFirst(0));
    assertEquals(10, map.getFirst(1).intValue());
  }

  @Test
  public void addWithKeyValue() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.add(1, 100);
    map.add(1, 10);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toSet(10, 100), map.get(1));
    assertTrue(map.containsKey(1));
    assertTrue(map.containsValue(CollectionLib.toSet(10, 100)));
  }

  @Test
  public void addWithEntry() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.add(new SimpleEntry<>(1, 100));
    map.add(new SimpleEntry<>(1, 10));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toSet(10, 100), map.get(1));
    assertTrue(map.containsKey(1));
    assertTrue(map.containsValue(CollectionLib.toSet(10, 100)));
  }

  @Test
  public void addWithSingleValueMap() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.add(1, 20);
    Map<Integer, Integer> map2 = new HashMap<>();
    map2.put(1, 10);
    map.add(map2);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toSet(10, 20), map.get(1));
    assertTrue(map.containsKey(1));
    assertTrue(map.containsValue(CollectionLib.toSet(10, 20)));
  }

  @Test
  public void addAllWithKeyValues() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10, 20, 10, 20, 30));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toSet(10, 20, 30), map.get(1));
  }

  @Test
  public void addAllWithEntry() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(new SimpleEntry<>(1, CollectionLib.toSet(10, 20, 10, 20, 30)));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toSet(10, 20, 30), map.get(1));
  }

  @Test
  public void addAllWithSetValueMap() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    SetValueMap<Integer, Integer> map2 = new SetValueHashMap<>();
    map2.addAll(1, CollectionLib.toSet(10, 20));
    map.add(1, 10);
    map.addAll(map2);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toSet(10, 20), map.get(1));
  }

  @Test
  public void setWithKeyValue() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.set(1, 1);
    map.set(1, 10);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(1, map.get(1).size());
  }

  @Test
  public void setWithEntry() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.set(1, 1);
    map.set(new SimpleEntry<>(1, 10));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(1, map.get(1).size());
  }

  @Test
  public void setWithSingleValueMap() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.set(1, 1);
    Map<Integer, Integer> map2 = new HashMap<>();
    map2.put(1, 10);
    map.set(map2);
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(10, map.getFirst(1).intValue());
    assertEquals(1, map.get(1).size());
  }

  @Test
  public void setAllWithKeyValues() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10, 20, 30));
    map.setAll(1, CollectionLib.toSet(10, 20, 10, 20, 30));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toSet(10, 20, 30), map.get(1));
  }

  @Test
  public void setAllWithEntry() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10, 20, 30));
    map.setAll(new SimpleEntry<>(1, CollectionLib.toSet(10, 20, 10, 20, 30)));
    assertNull(map.getFirst(0));
    assertEquals(1, map.size());
    assertEquals(CollectionLib.toSet(10, 20, 30), map.get(1));
  }

  @Test
  public void setAllWithSetValueMap() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10, 20, 30));
    map.addAll(2, CollectionLib.toSet(11, 12, 13));
    SetValueMap<Integer, Integer> map2 = new SetValueHashMap<>();
    map2.add(1, 10);
    map2.add(1, 20);
    map2.add(1, 10);
    map2.add(1, 20);
    map2.add(1, 30);
    map.setAll(map2);

    assertNull(map.getFirst(0));
    assertEquals(2, map.size());
    assertEquals(CollectionLib.toSet(10, 20, 30), map.get(1));
    assertEquals(CollectionLib.toSet(11, 12, 13), map.get(2));
  }


  @Test
  public void toMap() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(0, CollectionLib.toSet(1, 2, 3));
    map.addAll(1, CollectionLib.toSet(4, 5, 6));

    Map<Integer, Set<Integer>> map2 = map.toMap();

    assertEquals(2, map2.size());
    assertEquals(CollectionLib.toSet(1, 2, 3), map2.get(0));
    assertEquals(CollectionLib.toSet(4, 5, 6), map2.get(1));

    assertEquals(map, map2);
    assertEquals(map2, map);
    assertEquals(map, map);
    assertEquals(map2, map2);
  }

  @Test
  public void toSingleValueMap() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(0, CollectionLib.toSet(1, 2, 3));
    map.addAll(1, CollectionLib.toSet(4, 5, 6));

    Map<Integer, Integer> map2 = map.toSingleValueMap();
    assertEquals(2, map2.size());
    assertEquals(1, map2.get(0).intValue());
    assertEquals(4, map2.get(1).intValue());
  }

  @Test
  public void testClone() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(0, CollectionLib.toSet(1, 2, 3));
    map.addAll(1, CollectionLib.toSet(4, 5, 6));

    Map<Integer, Set<Integer>> map2 = ((SetValueHashMap<Integer, Integer>) map).clone();

    assertEquals(2, map2.size());
    assertEquals(CollectionLib.toSet(1, 2, 3), map2.get(0));
    assertEquals(CollectionLib.toSet(4, 5, 6), map2.get(1));

    assertEquals(map, map2);
    assertEquals(map2, map);
    assertEquals(map, map);
    assertEquals(map2, map2);
    assertTrue(map2 instanceof SetValueMap);
    assertTrue(map2 instanceof SetValueHashMap);
  }

  @Test
  public void clear() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10, 20, 30));
    map.addAll(2, CollectionLib.toSet(11, 12, 13));
    map.clear();
    assertEquals(0, map.size());
  }

  @Test
  public void keySet() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10, 20, 30));
    map.addAll(2, CollectionLib.toSet(11, 12, 13));

    List<Integer> keys = new ArrayList<>(map.keySet());
    assertEquals(2, keys.size());

    assertEquals(1, keys.get(0).intValue());
    assertEquals(2, keys.get(1).intValue());
  }

  @Test
  public void values() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10, 20, 30));
    map.addAll(2, CollectionLib.toSet(11, 12, 13));

    List<Set<Integer>> values = new ArrayList<>(map.values());
    assertEquals(2, values.size());

    assertEquals(CollectionLib.toSet(10, 20, 30), values.get(0));
    assertEquals(CollectionLib.toSet(11, 12, 13), values.get(1));
  }

  @Test
  public void entrySet() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10, 20, 30));
    map.addAll(2, CollectionLib.toSet(11, 12, 13));

    List<Entry<Integer, Set<Integer>>> entries = new ArrayList<>(map.entrySet());
    assertEquals(2, entries.size());

    assertEquals(1, entries.get(0).getKey().intValue());
    assertEquals(CollectionLib.toSet(10, 20, 30), entries.get(0).getValue());

    assertEquals(2, entries.get(1).getKey().intValue());
    assertEquals(CollectionLib.toSet(11, 12, 13), entries.get(1).getValue());
  }

  @Test
  public void testHashCode() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10));
    map.addAll(2, CollectionLib.toSet(11));
    assertEquals(20, map.hashCode());
  }

  @Test
  public void testToString() {
    SetValueMap<Integer, Integer> map = new SetValueHashMap<>();
    map.addAll(1, CollectionLib.toSet(10, 20));
    map.addAll(2, CollectionLib.toSet(11, 12));
    map.addAll(3, CollectionLib.toSet(11, 12));
    map.remove(3);
    assertEquals("{1=[20, 10], 2=[11, 12]}", map.toString());
  }

  private void assertNewEmptyMap(SetValueMap<?, ?> map) {
    assertNotNull(map);
    assertEquals(0, map.size());
    assertTrue(map.isEmpty());
    assertEquals(0, map.entrySet().size());
  }

}
