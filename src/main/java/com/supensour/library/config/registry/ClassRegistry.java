package com.supensour.library.config.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Suprayan Yapura
 * @since 1.1.1
 */
public class ClassRegistry {

  private final List<Class<?>> classes = new ArrayList<>();

  public void addClass(Class<?> clazz) {
    classes.add(Objects.requireNonNull(clazz, "Can't add null class"));
  }

  public List<Class<?>> getClasses() {
    return new ArrayList<>(classes);
  }

}
