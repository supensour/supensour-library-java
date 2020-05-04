package com.supensour.library.config.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass(value = {EnableSwagger2.class, ResponseEntity.class, DeferredResult.class})
public class SwaggerWebConfiguration implements SwaggerConfiguration {

  @Override
  public void addGenericModelSubstitutes(List<Class<?>> genericModelSubstitutes) {
    genericModelSubstitutes.add(ResponseEntity.class);
    genericModelSubstitutes.add(DeferredResult.class);
  }

}
