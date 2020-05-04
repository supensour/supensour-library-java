package com.supensour.library.libs;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class ErrorLib {

  private ErrorLib() {}

  public static final String SEPARATOR = ".";
  public static final String PATH = "path";

  public static Map<String, List<String>> mapFromBindingResult(BindingResult bindingResult) {
    return Optional.ofNullable(bindingResult)
        .filter(Errors::hasErrors)
        .map(Errors::getFieldErrors)
        .map(ErrorLib::mapFromFieldErrors)
        .orElseGet(HashMap::new);
  }

  public static Map<String, List<String>> mapFromFieldErrors(Collection<? extends FieldError> fieldErrors) {
    return Optional.ofNullable(fieldErrors)
        .map(errors -> errors.stream()
            .map(fieldError -> new SimpleEntry<>(fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(HashMap<String, List<String>>::new, CollectionLib::addToMultiValueMap, CollectionLib::addAllToMultiValueMap))
        .orElseGet(HashMap::new);
  }

  public static Map<String, List<String>> mapFromConstraintViolations(Collection<? extends ConstraintViolation<?>> constraintViolations) {
    return Optional.ofNullable(constraintViolations)
        .map(errors -> errors.stream()
            .map(ErrorLib::mapFromConstraintViolation)
            .collect(HashMap<String, List<String>>::new, CollectionLib::addAllToMultiValueMap, CollectionLib::addAllToMultiValueMap))
        .orElseGet(HashMap::new);
  }

  public static Map<String, List<String>> mapFromConstraintViolation(ConstraintViolation<?> constraintViolation) {
    return getConstraintViolationPaths(constraintViolation).stream()
        .map(path -> new SimpleEntry<>(path, constraintViolation.getMessage()))
        .collect(HashMap::new, CollectionLib::addToMultiValueMap, CollectionLib::addAllToMultiValueMap);
  }

  public static List<String> getConstraintViolationPaths(ConstraintViolation<?> constraintViolation) {
    return getConstraintViolationPaths(constraintViolation, PATH);
  }

  public static List<String> getConstraintViolationPaths(ConstraintViolation<?> constraintViolation, String path) {
    String[] paths = (String[]) constraintViolation.getConstraintDescriptor().getAttributes().get(path);
    if (CollectionLib.isEmpty(paths)) {
      String defaultPath = getConstraintViolationDefaultPath(constraintViolation);
      paths = new String[] { defaultPath };
    }
    return CollectionLib.toList(paths);
  }

  public static String getConstraintViolationDefaultPath(ConstraintViolation<?> constraintViolation) {
    return getConstraintViolationDefaultPath(constraintViolation, SEPARATOR);
  }

  public static String getConstraintViolationDefaultPath(ConstraintViolation<?> constraintViolation, String separator) {
    Path path = constraintViolation.getPropertyPath();
    List<String> nodes = new ArrayList<>();
    path.iterator().forEachRemaining(node -> Optional.ofNullable(node)
        .map(Path.Node::getName)
        .ifPresent(nodes::add));
    return String.join(separator, nodes);
  }

}
