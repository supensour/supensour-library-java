# AppInfoProperties
[Home](../README.md)

## Table of Contents
- [Table of Contents](#table-of-contents)
- [About](#about)
- [How to Use](#how-to-use)

## About
[AppInfoProperties](../src/main/java/com/supensour/library/properties/AppInfoProperties.java)
is a properties-class consists of the information about the application.

The information contains:
- name
- version
- description
- artifactId
- groupId

If spring boot actuator is active, these properties are also accessible from actuator `/info` endpoint.

## How to Use
1\. Add to application.properties:
```properties
info.app.name=@project.name@
info.app.version=@project.version@
info.app.description=@project.description@
info.app.artifactId=@project.artifactId@
info.app.groupId=@project.groupId@
```
2\. Add resource filtering in your pom.xml under `<build>` tag for the directory
where your application.properties is located:
```xml
<build>
  <resources>
    <resource>
      <directory>src/main/resources</directory>
      <filtering>true</filtering>
    </resource>
  </resources>
</build>
```
