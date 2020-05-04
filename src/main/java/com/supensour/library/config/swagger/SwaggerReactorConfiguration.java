package com.supensour.library.config.swagger;

import org.reactivestreams.Publisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass(value = {EnableSwagger2.class, Mono.class,  Publisher.class,  Flux.class})
public class SwaggerReactorConfiguration implements SwaggerConfiguration {

  @Override
  public void addGenericModelSubstitutes(List<Class<?>> genericModelSubstitutes) {
    genericModelSubstitutes.add(Mono.class);
    genericModelSubstitutes.add(Publisher.class);
    genericModelSubstitutes.add(Flux.class);
  }

}
