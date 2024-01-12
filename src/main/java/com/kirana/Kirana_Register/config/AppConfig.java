package com.kirana.Kirana_Register.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for Kirana Register application.
 * Enables caching and provides a bean for creating RestTemplate instances.
 */
@Configuration
@EnableCaching
public class AppConfig {

    /**
     * Creates and configures a RestTemplate bean.
     *
     * @return A RestTemplate instance for making HTTP requests.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}