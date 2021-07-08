package com.java_backend.apiserver.mongo_operation;
import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
import com.java_backend.apiserver.model.Activity;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import static com.mongodb.client.model.Filters.*;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
public class UserService {
    private ApplicationContext context;
    private MongoTemplate mongo;
    private String databaseName;
    private MongoDatabase db;
    private MongoCollection<Document> userCollection;
    private MongoClient mongoClient;
    private MongoTemplate mongoTemplate;

    public UserService() {
        try {
            context = new AnnotationConfigApplicationContext(MongoConfig.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mongoClient = context.getBean(MongoClient.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        databaseName = context.getBean(String.class);
        db = mongoClient.getDatabase(databaseName);
        userCollection = db.getCollection("User");
    }

    public HashMap<String, String> checkUserIDExist(String mobileID) {
        System.out.println(String.format("mobileID=%s", mobileID));
        Bson filter = eq("mobileId", mobileID);
        Document user = userCollection.find(filter).first();
        HashMap<String,String> response  = new HashMap<String,String>();
        if(user==null){
            Document newUserProfile = new Document();
            newUserProfile.append("mobileId", mobileID);
            InsertOneResult result= userCollection.insertOne(newUserProfile);
            response.put("result", result.getInsertedId().asString().toString());
        }else{
            response.put("result", user.get("_id").toString());
        }
        return response;
    }

}
