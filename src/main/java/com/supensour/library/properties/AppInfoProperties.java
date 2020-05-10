package com.supensour.library.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
@Data
@ConfigurationProperties("info.app")
public class AppInfoProperties {

  private String name;

  private String version;

  private String description;

  private String artifactId;

  private String groupId;

}
