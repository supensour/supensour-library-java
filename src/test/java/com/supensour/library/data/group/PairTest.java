package com.supensour.library.data.group;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class PairTest {

  @Test
  public void data() {
    Pair<String, String> pair = Pair.empty();
    pair.setFirst("Suprayan");
    pair.setSecond("Yapura");
    assertEquals("Suprayan", pair.getFirst());
    assertEquals("Yapura", pair.getSecond());
  }

  @Test
  public void of() {
    Pair<String, String> pair = Pair.of("Suprayan", "Yapura");
    assertEquals("Suprayan", pair.getFirst());
    assertEquals("Yapura", pair.getSecond());
  }

}
