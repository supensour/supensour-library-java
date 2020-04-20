package com.supensour.library.data;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since March 29, 2020
 */
public class TetradTest {

  @Test
  public void data() {
    Tetrad<String, String, Integer, Integer> tetrad = Tetrad.empty();
    tetrad.setFirst("Suprayan");
    tetrad.setSecond("Yapura");
    tetrad.setThird(1);
    tetrad.setFourth(2);
    assertEquals("Suprayan", tetrad.getFirst());
    assertEquals("Yapura", tetrad.getSecond());
    assertEquals(1, tetrad.getThird().intValue());
    assertEquals(2, tetrad.getFourth().intValue());
  }

  @Test
  public void of() {
    Tetrad pair = Tetrad.of("Suprayan", "Yapura", 1, 2);
    assertEquals("Suprayan", pair.getFirst());
    assertEquals("Yapura", pair.getSecond());
    assertEquals(1, pair.getThird());
    assertEquals(2, pair.getFourth());
  }

}
