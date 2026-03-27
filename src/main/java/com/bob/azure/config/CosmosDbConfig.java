package com.bob.azure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@Configuration
@EnableCosmosRepositories(basePackages = "com.bob.azure.repository.cosmos")
public class CosmosDbConfig extends AbstractCosmosConfiguration {

    @Value("${spring.cloud.azure.cosmos.database}")
    private String database;

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
