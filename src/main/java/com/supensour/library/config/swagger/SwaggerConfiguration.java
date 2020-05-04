package com.supensour.library.config.swagger;

import springfox.documentation.service.Parameter;

import java.util.List;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
public interface SwaggerConfiguration {

  default void addGenericModelSubstitutes(List<Class<?>> genericModelSubstitutes) {

  }

  default void addIgnoredParameterTypes(List<Class<?>> ignoredParameterTypes) {

  }

  default void addGlobalParameter(List<Parameter> globalParameters) {

  }

}
