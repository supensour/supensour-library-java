package com.supensour.library.config.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import rx.Observable;
import rx.Single;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass(value = {EnableSwagger2.class, Single.class, Observable.class})
public class SwaggerRxJavaConfiguration implements SwaggerConfiguration {

  @Override
  public void addGenericModelSubstitutes(List<Class<?>> genericModelSubstitutes) {
    genericModelSubstitutes.add(Single.class);
    genericModelSubstitutes.add(Observable.class);
  }

}
