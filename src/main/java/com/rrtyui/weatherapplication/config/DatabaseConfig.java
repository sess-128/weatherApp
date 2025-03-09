package com.rrtyui.weatherapplication.config;

import org.flywaydb.core.internal.jdbc.DriverDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public DriverDataSource getDataSource() {
        return null;
    }
}
