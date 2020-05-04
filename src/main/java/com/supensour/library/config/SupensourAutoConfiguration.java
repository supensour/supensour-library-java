package com.supensour.library.config;

import com.supensour.library.properties.AppInfoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Suprayan Yapura
 * @since 1.1.0
 */
@Configuration
@EnableConfigurationProperties(value = {
    AppInfoProperties.class
})
public class SupensourAutoConfiguration {

}
