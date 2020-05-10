package com.supensour.library.config.swagger;

import com.supensour.library.config.registry.ClassRegistry;
import org.reactivestreams.Publisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass(value = {EnableSwagger2.class, Mono.class,  Publisher.class,  Flux.class})
public class SwaggerReactorConfiguration implements SwaggerConfiguration {

  @Override
  public void addGenericModelSubstitutes(ClassRegistry registry) {
    registry.addClass(Mono.class);
    registry.addClass(Publisher.class);
    registry.addClass(Flux.class);
  }

}
