package com.supensour.library.config.swagger;

import com.supensour.library.config.registry.ClassRegistry;
import com.supensour.library.config.registry.ParameterRegistry;
import com.supensour.library.properties.AppInfoProperties;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
@Configuration
@ConditionalOnClass(EnableSwagger2.class)
public class SwaggerAutoConfiguration implements ApplicationContextAware {

  @Autowired
  private AppInfoProperties appInfoProperties;

  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Bean
  @ConditionalOnMissingBean
  public Docket swaggerDocket() {
    List<SwaggerConfiguration> configs = getSwaggerConfigurations();
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .build()
        .apiInfo(buildApiInfo())
        .useDefaultResponseMessages(false)
        .genericModelSubstitutes(getGenericModelSubstitutes(configs))
        .ignoredParameterTypes(getIgnoredParameterTypes(configs))
        .globalOperationParameters(getGlobalParameters(configs));
  }

  private List<SwaggerConfiguration> getSwaggerConfigurations() {
    return new ArrayList<>(applicationContext.getBeansOfType(SwaggerConfiguration.class).values());
  }

  private ApiInfo buildApiInfo() {
    return new ApiInfoBuilder()
        .title(getTitle())
        .version(getVersion())
        .description(getDescription())
        .build();
  }

  private String getTitle() {
    String title = Optional.ofNullable(appInfoProperties.getName())
        .filter(StringUtils::hasText)
        .orElseGet(appInfoProperties::getArtifactId);
    return Optional.ofNullable(title)
        .filter(StringUtils::hasText)
        .orElse("Api Documentation");
  }

  private String getVersion() {
    return Optional.ofNullable(appInfoProperties.getVersion())
        .filter(StringUtils::hasText)
        .orElse("1.0");
  }

  private String getDescription() {
    return Optional.ofNullable(appInfoProperties.getDescription())
        .filter(StringUtils::hasText)
        .orElse("Api Documentation");
  }

  private Class<?>[] getGenericModelSubstitutes(List<SwaggerConfiguration> configs) {
    ClassRegistry registry = new ClassRegistry();
    configs.forEach(config -> config.addGenericModelSubstitutes(registry));
    return registry.getClasses().toArray(Class[]::new);
  }

  private Class<?>[] getIgnoredParameterTypes(List<SwaggerConfiguration> configs) {
    ClassRegistry registry = new ClassRegistry();
    configs.forEach(config -> config.addIgnoredParameterTypes(registry));
    return registry.getClasses().toArray(Class[]::new);
  }

  private List<Parameter> getGlobalParameters(List<SwaggerConfiguration> configs) {
    ParameterRegistry registry = new ParameterRegistry();
    configs.forEach(config -> config.addGlobalParameter(registry));
    return registry.getParameters();
  }

}
