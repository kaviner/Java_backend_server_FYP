package com.java_backend.apiserver.config.mongodb_config;

import java.io.File;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    public MongoDBConnectionInfo connectonInfo;
    public void loadMongoConfig(){
        try {
            File configFile = new File("src\\main\\resources\\static\\MongoDBConfig.yaml");
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            om.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            this.connectonInfo = om.readValue(configFile,MongoDBConnectionInfo.class);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Bean
    public MongoClient mongoClient() {
        try{
            loadMongoConfig();
            ConnectionString connectionString = new ConnectionString(this.connectonInfo.getConnectionString());
            MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
            System.out.println("Init MongoDB client ,"+this.connectonInfo.toString());
            return MongoClients.create(mongoClientSettings);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Cannot init MongoDB client");
        }
        return null;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        System.out.println(connectonInfo.getDatabaseName());
        return new MongoTemplate(mongoClient(), connectonInfo.getDatabaseName());
    }

    @Bean
    public String databaseName() {
        return this.connectonInfo.getDatabaseName();
    }
}