package com.supensour.library.model.error;

import com.supensour.library.model.map.SetValueMap;

import java.io.Serializable;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public interface ErrorMapping<T extends ErrorMapping<T>> extends Serializable {

  void setErrors(SetValueMap<String, String> errors);

  SetValueMap<String, String> getErrors();

  default T addError(String key, String value) {
    getErrors().add(key, value);
    return (T) this;
  }

}
