# Supensour Springboot Library

[ ![Download](https://api.bintray.com/packages/supensour/maven-releases/com.supensour%3Asupensour-springboot-library/images/download.svg) ](https://bintray.com/supensour/maven-releases/com.supensour%3Asupensour-springboot-library/_latestVersion)
![Build](https://github.com/supensour/supensour-springboot-library/workflows/Build/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.supensour%3Asupensour-springboot-library&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.supensour%3Asupensour-springboot-library)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.supensour%3Asupensour-springboot-library&metric=coverage)](https://sonarcloud.io/dashboard?id=com.supensour%3Asupensour-springboot-library)

## Table of Contents
- [Table of Contents](#table-of-contents)
- [About](#about)
- [Preparation](#preparation)
- [Libraries](#libraries)
- [Multi Value Map](#multi-value-map)
- [Web DTO](#web-dto)
- [Error Controller](#error-controller)
- [Responding Exception](#responding-exception)
- [More](#more)

## About
This library is for used with spring boot project. Detailed documentation will be provided soon.

## Preparation
1\. Add this maven dependency.
```xml
<dependency>
  <groupId>com.supensour</groupId>
  <artifactId>supensour-springboot-library</artifactId>
  <version>${supensour-library.version}</version>
</dependency>
```

2\. Add this repository.
```xml
<repository>
    <id>bintray-supensour-releases</id>
    <name>bintray-supensour-releases</name>
    <url>https://dl.bintray.com/supensour/maven-releases</url>
</repository>
```
In addition, this library has been included in [Bintray JCenter](https://bintray.com/bintray/jcenter).
```xml
<repository>
    <id>bintray-jcenter</id>
    <name>bintray-jcenter</name>
    <url>https://jcenter.bintray.com</url>
</repository>
```

## Libraries
- [CollectionLib](src/main/java/com/supensour/library/libs/CollectionLib.java)
- [ConverterLib](src/main/java/com/supensour/library/libs/ConverterLib.java)
- [DateTimeLib](src/main/java/com/supensour/library/libs/DateTimeLib.java)
- [ErrorLib](src/main/java/com/supensour/library/libs/ErrorLib.java)
- [ErrorTolerantLib](src/main/java/com/supensour/library/libs/ErrorTolerantLib.java)
- [PagingLib](src/main/java/com/supensour/library/libs/PagingLib.java)\
  `org.springframework.data:spring-data-commons` is needed.
- [RandomLib](src/main/java/com/supensour/library/libs/RandomLib.java)
- [ResponseLib](src/main/java/com/supensour/library/libs/ResponseLib.java)\
  `org.springframework.data:spring-data-commons` is needed.
- [StringLib](src/main/java/com/supensour/library/libs/StringLib.java)

## Multi Value Map
Multi value map interfaces and implementations. Serialization/deserialization mechanism is yet to be provided,
so it's not ready to be used as web DTOs.

Interfaces:
- [BaseMultiValueMap](src/main/java/com/supensour/library/model/map/BaseMultiValueMap.java)
- [MultiValueMap](src/main/java/com/supensour/library/model/map/MultiValueMap.java)
- [SetValueMap](src/main/java/com/supensour/library/model/map/SetValueMap.java)

Implementations:
- [MultiValueHashMap](src/main/java/com/supensour/library/model/map/impl/MultiValueHashMap.java)
- [SetValueHashMap](src/main/java/com/supensour/library/model/map/impl/SetValueHashMap.java)

## Web DTO
List of DTOs that might be used for web controller:
- [PagingRequest](src/main/java/com/supensour/library/model/web/PagingRequest.java)
- [PagingResponse](src/main/java/com/supensour/library/model/web/PagingResponse.java)
- [Response](src/main/java/com/supensour/library/model/web/Response.java)
- [SortingRequest](src/main/java/com/supensour/library/model/web/SortingRequest.java)

Some related libs to operate with those DTOs are:
- [PagingLib](src/main/java/com/supensour/library/libs/PagingLib.java)
- [ResponseLib](src/main/java/com/supensour/library/libs/ResponseLib.java)

## Error Controller
To use this, create an error controller and implements
[BaseErrorControllerHandler](src/main/java/com/supensour/library/web/error/BaseErrorControllerHandler.java).

Example with Slf4j logger:
```java
@Slf4j
@RestControllerAdvice
public class ErrorController implements BaseErrorControllerHandler {

  @Override
  public Logger getLogger() {
    return log;
  }

}
```

## Responding Exception
[RespondingException](src/main/java/com/supensour/library/model/error/RespondingException.java)
is a runtime and response-like exception that is ready to be converted to
[Response](src/main/java/com/supensour/library/model/web/Response.java).

This exception is also handled by
[BaseErrorControllerHandler](src/main/java/com/supensour/library/web/error/BaseErrorControllerHandler.java).


## More
- [AppInfoProperties](docs/app-info-properties.md)
- [ContextAwareThreadPoolTaskExecutor](docs/context-aware-thread-pool-task-executor.md)
- [SpringFox Swagger Configuration](docs/springfox-swagger-configuration.md)
