package com.supensour.library.data;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since March 29, 2020
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
