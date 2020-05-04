package com.supensour.library.model.error;

import com.supensour.library.libs.CollectionLib;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public interface ErrorMapping<T extends ErrorMapping<T>> extends Serializable {

  void setErrors(Map<String, List<String>> errors);

  Map<String, List<String>> getErrors();

  default T addError(String key, String value) {
    Map<String, List<String>> errors = CollectionLib.addToMultiValueMap(getErrors(), key, value);
    setErrors(errors);
    return (T) this;
  }

  default T addErrors(String key, String... value) {
    return addErrors(key, CollectionLib.toList(value));
  }

  default T addErrors(String key, Collection<String> value) {
    Map<String, List<String>> errors = CollectionLib.addAllToMultiValueMap(getErrors(), key, value);
    setErrors(errors);
    return (T) this;
  }

}
