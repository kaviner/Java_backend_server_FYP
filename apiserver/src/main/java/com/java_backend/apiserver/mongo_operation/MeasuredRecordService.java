package com.java_backend.apiserver.mongo_operation;

import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
import com.java_backend.apiserver.model.MeasuredRecord;
import com.java_backend.apiserver.model.Schedule;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import static com.mongodb.client.model.Filters.*;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MeasuredRecordService {
    private ApplicationContext context;
    private MeasuredRecord measuredRecord;
    private MongoTemplate mongo;
    private String databaseName;
    private MongoDatabase db;
    private MongoCollection<Document> scheduleCollection;
    private MongoClient mongoClient;
    private MongoTemplate mongoTemplate;

    public MeasuredRecordService() {
        try {
            context = new AnnotationConfigApplicationContext(MongoConfig.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mongoClient = context.getBean(MongoClient.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        databaseName = context.getBean(String.class);
        db = mongoClient.getDatabase(databaseName);
        scheduleCollection = db.getCollection("MeasuredRecord");
    }

    public MeasuredRecordService(MeasuredRecord measuredRecord) {
        this.measuredRecord=measuredRecord;
        try{
          context = new AnnotationConfigApplicationContext(MongoConfig.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        mongoClient = context.getBean(MongoClient.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        databaseName = context.getBean(String.class);
        db=mongoClient.getDatabase(databaseName);
        scheduleCollection = db.getCollection("MeasuredRecord");
    }

    public String addMeasurRecord(MeasuredRecord measuredRecordService) {
        return null;
    }
}