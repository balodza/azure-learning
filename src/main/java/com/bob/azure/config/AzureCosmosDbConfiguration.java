package com.bob.azure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@Configuration
@EnableCosmosRepositories(basePackages = "com.bob.azure.repository.cosmos")
public class AzureCosmosDbConfiguration extends AbstractCosmosConfiguration {

//    @Value("${azure.cosmosdb.uri}")
//    private String uri;
//
//    @Value("${azure.cosmosdb.key}")
//    private String key;

    @Value("${spring.cloud.azure.cosmos.database}")
    private String database;

//    private CosmosKeyCredential cosmosKeyCredential;
//
//    @Bean
//    public CosmosDBConfig getConfig() {
//        this.cosmosKeyCredential = new CosmosKeyCredential(key);
//        CosmosDBConfig cosmosdbConfig = CosmosDBConfig.builder(uri, this.cosmosKeyCredential, database)
//            .build();
//        return cosmosdbConfig;
//    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
