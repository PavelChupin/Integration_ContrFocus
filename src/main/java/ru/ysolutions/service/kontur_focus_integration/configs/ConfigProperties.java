package ru.ysolutions.service.kontur_focus_integration.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "konturfocus")
@PropertySource("classpath:application.yaml")
public class ConfigProperties {
        private String url;
        private String key;
}
