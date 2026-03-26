package com.bob.azure.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.bob.azure.repository.jpa")
public class JpaConfig {
}
