# Supensour Springboot Library

[ ![Download](https://api.bintray.com/packages/supensour/maven-releases/com.supensour%3Asupensour-springboot-library/images/download.svg) ](https://bintray.com/supensour/maven-releases/com.supensour%3Asupensour-springboot-library/_latestVersion)
![Build](https://github.com/supensour/supensour-springboot-library/workflows/Build/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.supensour%3Asupensour-springboot-library&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.supensour%3Asupensour-springboot-library)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.supensour%3Asupensour-springboot-library&metric=coverage)](https://sonarcloud.io/dashboard?id=com.supensour%3Asupensour-springboot-library)

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

## Libs
1. CollectionLib
2. ConverterLib
3. DateTimeLib
4. ErrorLib
5. PagingLib\
   `org.springframework.data:spring-data-commons` is needed.
6. RandomLib
7. ResponseLib\
   `org.springframework.data:spring-data-commons` is needed.
8. StringLib
