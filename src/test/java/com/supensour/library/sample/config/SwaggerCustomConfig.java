package com.supensour.library.sample.config;

import com.supensour.library.config.registry.ClassRegistry;
import com.supensour.library.config.registry.ParameterRegistry;
import com.supensour.library.config.swagger.SwaggerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ParameterBuilder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
@Configuration
public class SwaggerCustomConfig implements SwaggerConfiguration {

  @Override
  public void addGenericModelSubstitutes(ClassRegistry registry) {
    registry.addClass(ResponseEntity.class);
    SwaggerConfiguration.super.addGenericModelSubstitutes(registry);
  }

  @Override
  public void addIgnoredParameterTypes(ClassRegistry registry) {
    registry.addClass(HttpServletRequest.class);
    SwaggerConfiguration.super.addIgnoredParameterTypes(registry);
  }

  @Override
  public void addGlobalParameter(ParameterRegistry registry) {
    registry.addParameter(new ParameterBuilder().name("param").build());
    SwaggerConfiguration.super.addGlobalParameter(registry);
  }

}
