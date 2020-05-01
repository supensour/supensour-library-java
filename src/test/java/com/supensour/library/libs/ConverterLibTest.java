package com.supensour.library.libs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class ConverterLibTest {

  @Test
  public void copyPropertiesSingle() {
    ModelA a = new ModelA("valueA", "valueB");
    ModelB b = ConverterLib.copyProperties(a, new ModelB());
    assertEquals("valueA", b.fieldA);
    assertEquals("valueB", b.fieldB);

    b = ConverterLib.copyProperties(a, new ModelB(), "fieldB");
    assertEquals("valueA", b.fieldA);
    assertNull(b.fieldB);

    b = ConverterLib.copyProperties(a, new ModelB(), "fieldA", "fieldB");
    assertNull(b.fieldA);
    assertNull(b.fieldB);

    b = ConverterLib.copyProperties(a, new ModelB("A", "B"), "fieldA", "fieldB");
    assertEquals("A", b.fieldA);
    assertEquals("B", b.fieldB);
  }

  @Test
  public void copyPropertiesSingleSupplier() {
    ModelA a = new ModelA("valueA", "valueB");
    ModelB b = ConverterLib.copyProperties(a, ModelB::new);
    assertEquals("valueA", b.fieldA);
    assertEquals("valueB", b.fieldB);

    b = ConverterLib.copyProperties(a, ModelB::new, "fieldB");
    assertEquals("valueA", b.fieldA);
    assertNull(b.fieldB);
  }

  @Test
  public void copyPropertiesSingleSupplier_nullSupplier() {
    try {
      ModelA a = new ModelA("valueA", "valueB");
      ConverterLib.copyProperties(a, null);
    } catch (RuntimeException e) {
      assertEquals("Target supplier must not be null", e.getMessage());
    }
  }

  @Test
  public void copyPropertiesList() {
    List<ModelA> as = new ArrayList<>();
    as.add(new ModelA("valueA1", "valueB1"));
    as.add(new ModelA("valueA2", "valueB2"));
    List<ModelB> bs = ConverterLib.copyProperties(as, ModelB::new);
    assertEquals("valueA1", bs.get(0).fieldA);
    assertEquals("valueB1", bs.get(0).fieldB);
    assertEquals("valueA2", bs.get(1).fieldA);
    assertEquals("valueB2", bs.get(1).fieldB);

    bs = ConverterLib.copyProperties(null, ModelB::new);
    assertEquals(0, bs.size());

    bs = ConverterLib.copyProperties(new ArrayList<>(), ModelB::new);
    assertEquals(0, bs.size());
  }

  @Test
  public void copyPropertiesList_nullSupplier() {
    try {
      ConverterLib.copyProperties(null, null);
    } catch (RuntimeException e) {
      assertEquals("Target supplier must not be null", e.getMessage());
    }
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class ModelA {
    String fieldA;
    String fieldB;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class ModelB {
    String fieldA;
    String fieldB;
  }

}
