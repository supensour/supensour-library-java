package com.supensour.library.data.group;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class TrioTest {

  @Test
  public void data() {
    Trio<String, String, Integer> trio = Trio.empty();
    trio.setFirst("Suprayan");
    trio.setSecond("Yapura");
    trio.setThird(1);
    assertEquals("Suprayan", trio.getFirst());
    assertEquals("Yapura", trio.getSecond());
    assertEquals(1, trio.getThird().intValue());
  }

  @Test
  public void of() {
    Trio<String, String, Integer> trio = Trio.of("Suprayan", "Yapura", 1);
    assertEquals("Suprayan", trio.getFirst());
    assertEquals("Yapura", trio.getSecond());
    assertEquals(1, trio.getThird().intValue());
  }

}
