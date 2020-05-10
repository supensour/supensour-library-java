package com.supensour.library.config.swagger;

import com.supensour.library.config.registry.ClassRegistry;
import com.supensour.library.config.registry.ParameterRegistry;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
public interface SwaggerConfiguration {

  default void addGenericModelSubstitutes(ClassRegistry registry) {

  }

  default void addIgnoredParameterTypes(ClassRegistry registry) {

  }

  default void addGlobalParameter(ParameterRegistry registry) {

  }

}
