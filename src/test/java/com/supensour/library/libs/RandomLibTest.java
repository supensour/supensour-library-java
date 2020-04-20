package com.supensour.library.libs;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since March 29, 2020
 */
public class RandomLibTest {

  @Test
  public void testNumber() {
    for (int i=0; i<20; i++) {
      assertTrue(RandomLib.generate(RandomLib.RandomType.DIGIT, 8).matches("^[0-9]{8}$"));
    }
  }

  @Test
  public void testAlphabet() {
    for (int i=0; i<20; i++) {
      assertTrue(RandomLib.generate(RandomLib.RandomType.ALPHABET, 8).matches("^[a-zA-Z]{8}$"));
    }
  }

  @Test
  public void testAlphaNumeric() {
    for (int i=0; i<20; i++) {
      assertTrue(RandomLib.generate(RandomLib.RandomType.ALPHANUMERIC, 8).matches("^[a-zA-Z0-9]{8}$"));
    }
  }

}
