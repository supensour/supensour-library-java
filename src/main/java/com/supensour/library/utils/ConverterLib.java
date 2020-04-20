package com.supensour.library.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Suprayan Yapura
 * @since April 04, 2020
 */
public class ConverterLib {

  private ConverterLib() {}

  /**
   * Copy properties from source to target.
   * If either source/target object is null, the original target will be returned.
   *
   * @param source  source object to be copied from
   * @param target  target object to be copied to
   * @param ignoreProperties list of property names to be ignored
   *
   * @return the target
   */
  public static <R, T> T copyProperties(R source, T target, String... ignoreProperties) {
    Optional.ofNullable(source)
        .filter(s -> Objects.nonNull(target))
        .ifPresent(s -> BeanUtils.copyProperties(source, target, ignoreProperties));
    return target;
  }

  /**
   * Copy properties from source to the target object provided by targetSupplier.
   * If either source/target object is null, the original target will be returned.
   *
   * @param source          source object to be copied from
   * @param targetSupplier  target supplier to provide target object
   * @param ignoreProperties list of property names to be ignored
   *
   * @throws NullPointerException if targetSupplier is null
   * @return the target
   */
  public static <R, T> T copyProperties(R source, Supplier<T> targetSupplier, String... ignoreProperties) {
    Objects.requireNonNull(targetSupplier, "Target supplier must not be null");
    return copyProperties(source, targetSupplier.get(), ignoreProperties);
  }

  /**
   * Copy properties from a collection of sources to the target object provided by targetSupplier.
   * For each source, if either the source/target object is null, the original target will be returned.
   * In case of empty/null sources, empty List will be returned.
   *
   * @param sources         a collection of source objects to be copied from
   * @param targetSupplier  target supplier to provide target object
   * @param ignoreProperties list of property names to be ignored
   *
   * @throws NullPointerException if targetSupplier is null
   * @return the target
   */
  public static <R, T> List<T> copyProperties(Collection<R> sources, Supplier<T> targetSupplier, String... ignoreProperties) {
    Objects.requireNonNull(targetSupplier, "Target supplier must not be null");
    return Optional.ofNullable(sources)
        .map(allSources -> allSources.stream()
            .map(source -> copyProperties(source, targetSupplier, ignoreProperties))
            .collect(Collectors.toList()))
        .orElseGet(ArrayList::new);
  }

}
