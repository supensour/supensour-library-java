package com.supensour.library.config.registry;

import springfox.documentation.service.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Suprayan Yapura
 * @since 1.1.1
 */
public class ParameterRegistry {

  private final List<Parameter> parameters = new ArrayList<>();

  public void addParameter(Parameter clazz) {
    parameters.add(Objects.requireNonNull(clazz, "Can't add null parameter"));
  }

  public List<Parameter> getParameters() {
    return new ArrayList<>(parameters);
  }

}
