package com.example.t505esports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
public class Corsconfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Tu frontend
        config.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, etc.)
        config.addAllowedHeader("*"); // Permitir todos los encabezados
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
