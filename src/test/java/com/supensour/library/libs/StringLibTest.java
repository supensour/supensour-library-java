package com.supensour.library.libs;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since March 29, 2020
 */
public class StringLibTest {

  @Test
  public void match() {
    List<String> matches = StringLib.match("supra-supra", "(su|ra)");
    assertEquals(4, matches.size());
    assertEquals("su", matches.get(0));
    assertEquals("ra", matches.get(1));
    assertEquals("su", matches.get(2));
    assertEquals("ra", matches.get(3));
  }

  @Test
  public void match_max() {
    List<String> matches = StringLib.match("supra-supra", "(su|ra)", 3);
    assertEquals(3, matches.size());
    assertEquals("su", matches.get(0));
    assertEquals("ra", matches.get(1));
    assertEquals("su", matches.get(2));
  }

  @Test
  public void split() {
    List<String> results = StringLib.split("supra-supra--supra", '-');
    assertEquals(4, results.size());
    assertEquals("supra", results.get(0));
    assertEquals("supra", results.get(1));
    assertEquals("", results.get(2));
    assertEquals("supra", results.get(3));

    results = StringLib.split("supra", '-');
    assertEquals(1, results.size());
    assertEquals("supra", results.get(0));
  }

  @Test
  public void split_max() {
    List<String> results = StringLib.split("supra-supra--supra", '-', 2);
    assertEquals(2, results.size());
    assertEquals("supra", results.get(0));
    assertEquals("supra", results.get(1));
  }

  @Test
  public void subString_preliminary_terminator() {
    String text = "Lorem ipsum dolor sit";
    assertEquals("ipsum dolor", StringLib.subString(text, "Lorem ", " sit"));
    assertEquals("Lorem ipsum dolor", StringLib.subString(text, "", " sit"));
    assertEquals("Lorem ipsum dolor", StringLib.subString(text, null, " sit"));
    assertEquals("ipsum dolor sit", StringLib.subString(text, "Lorem ", " sits"));
    assertEquals("", StringLib.subString(text, "Lorems", " sit"));
  }

  @Test
  public void subString_beginIndex_terminator() {
    String text = "Lorem ipsum dolor sit";
    assertEquals("e", StringLib.subString(text, 3, "m"));
    assertEquals("", StringLib.subString(text, 10, "m"));
    assertEquals("dolor sit", StringLib.subString(text, 12, null));
    assertEquals("dolor sit", StringLib.subString(text, 12, ""));
    assertEquals(" dolor sit", StringLib.subString(text, 11, "m"));
    assertEquals("Lore", StringLib.subString(text, 0, "m"));
    assertEquals("ipsu", StringLib.subString(text, 6, "m"));
  }

  @Test
  public void subString_preliminary_endIndex() {
    String text = "Lorem ipsum dolor sit";
    assertEquals("ipsum", StringLib.subString(text, "Lorem ", 11));
    assertEquals("ipsum dolor sit", StringLib.subString(text, "Lorem ", 255));
    assertEquals("", StringLib.subString(text, "Lorems", 11));
    assertEquals("Lorem", StringLib.subString(text, "", 5));
    assertEquals("Lorem", StringLib.subString(text, null, 5));
    assertEquals("Lorem ipsum dolor sit", StringLib.subString(text, null, 255));
  }

}
