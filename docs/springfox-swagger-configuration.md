# SpringFox Swagger Configuration
[Home](..)

## Table of Contents
- [Table of Contents](#table-of-contents)
- [About SpringFox Swagger](#about-springfox-swagger)
- [Note](#note)
- [How to Use](#how-to-use)
- [App Information](#app-information)
- [Custom Configuration](#custom-configuration)
- [Included Custom Configuration](#included-custom-configurations)

## About SpringFox Swagger
SpringFox Swagger is an automated JSON API documentation. It's used for APIs built with Spring.

Read more:
- [SpringFox Website](http://springfox.github.io/springfox)
- [SpringFox Github](https://github.com/springfox/springfox)
- [How to Use - Baeldung](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)

## Note
Supensour provides a simple configuration mechanism for the use of SpringFox Swagger.
If no bean of `springfox.documentation.spring.web.plugins.Docket` is found,
the default one will be the one from `com.supensour.library.config.swagger.SwaggerAutoConfiguration#swaggerDocket()`
(check [SwaggerAutoConfiguration](../src/main/java/com/supensour/library/config/swagger/SwaggerAutoConfiguration.java)).

## How to Use
This is an optional feature. Springfox swagger documentation UI can be enabled by doing the steps below:

1\. Add springfox dependency (the version used here is 2.9.2)
```xml
<depedencies>
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>${springfox.version}</version>
    </dependency>
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>${springfox.version}</version>
    </dependency>
</depedencies>
```
2\. Add `@EnableSwagger2` annotation
```java
@EnableSwagger2
@SpringBootApplication
public class MySpringBootApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(MySpringBootApplication.class, args);
  }

}
```

## App Information
API's information is retrieved
from [AppInfoProperties](../src/main/java/com/supensour/library/properties/AppInfoProperties.java).
The fields used for API's information are:
- name/artifactId
- version
- description

This only works if default defined `Docket` at here is being used.

Read app info properties [documentation](app-info-properties.md).

## Custom Configuration
To use the custom configuration provided by Supensour, create a `@Configuration` class and implements
[SwaggerConfiguration](../src/main/java/com/supensour/library/config/swagger/SwaggerConfiguration.java) interface.
This configuration interface can:
- Add generic classes that should be substituted with its type parameter.\
  i.e: `ResponseEntity<T>` will be replaced by `T`.
- Ignored parameter types.
- Global parameters for all APIs.

Example:
```java
@Configuration
public class CustomSwaggerConfiguration implements SwaggerConfiguration {

  @Override
  public void addGenericModelSubstitutes(ClassRegistry registry) {
    registry.addClass(ResponseEntity.class);
  }

  @Override
  public void addIgnoredParameterTypes(ClassRegistry registry) {
    registry.addClass(Foo.class);
  }

  @Override
  public void addGlobalParameter(ParameterRegistry registry) {
    Parameter accessTokenParameter = new ParameterBuilder()
        .name("access_token")
        .parameterType("query")
        .modelRef(new ModelRef("string"))
        .required(false)
        .description("Access Token")
        .defaultValue("testing-token")
        .build();

    Parameter apiKeyParameter = new ParameterBuilder()
        .name("X-API-Key")
        .parameterType("header")
        .modelRef(new ModelRef("string"))
        .required(true)
        .description("API Key")
        .defaultValue("testing-api-key")
        .build();

    registry.addParameter(accessTokenParameter);
    registry.addParameter(apiKeyParameter);
  }

}
```

## Included Custom Configurations
Below are list of included swagger custom configurations:
- [SwaggerReactorConfiguration](../src/main/java/com/supensour/library/config/swagger/SwaggerReactorConfiguration.java)\
  Generic model substitutes:
  - `reactor.core.publisher.Mono`
  - `reactor.core.publisher.Flux`
  - `org.reactivestreams.Publisher`
- [SwaggerRxJavaConfiguration.java](../src/main/java/com/supensour/library/config/swagger/SwaggerRxJavaConfiguration.java)\
  Generic model substitutes:
  - `rx.Single`
  - `rx.Observable`
- [SwaggerWebConfiguration](../src/main/java/com/supensour/library/config/swagger/SwaggerWebConfiguration.java)\
  Generic model substitutes:
  - `org.springframework.http.ResponseEntity`
  - `org.springframework.web.context.request.async.DeferredResult`
