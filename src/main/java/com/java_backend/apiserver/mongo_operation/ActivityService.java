package com.java_backend.apiserver.mongo_operation;

import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
import com.java_backend.apiserver.model.Activity;
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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;


public class ActivityService {
    private ApplicationContext context;
    private Activity activity;
    private MongoTemplate mongo;
    private String databaseName;
    private MongoDatabase db;
    private MongoCollection<Document> activityCollection;
    private MongoClient mongoClient;
    private MongoTemplate mongoTemplate;

    public ActivityService() {
        try {
            context = new AnnotationConfigApplicationContext(MongoConfig.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mongoClient = context.getBean(MongoClient.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        databaseName = context.getBean(String.class);
        db = mongoClient.getDatabase(databaseName);
        activityCollection = db.getCollection("Activity");
    }


    public String getAllActivityCategory() {
        JSONArray results = new JSONArray();
        try {
            MongoCursor<String> cursor=null;
            cursor = activityCollection.distinct("category",String.class).iterator();
            while (cursor.hasNext()) {
                String document = cursor.next();
                results.put(document);
            }
            System.out.println(String.format("QueryResult=%s", results.toString()));
            return results.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public HashMap<String,String> addNewActivityIfNotExist(Activity activity){
        HashMap<String, String> map = new HashMap<>();
        try {
            Document activityRecord=null;
            Bson filter = and(
                eq("userID", activity.getUserID()),
                and(
                    eq("name", activity.getName()),
                    eq("category",activity.getCategory())
                )
            );
            activityRecord = activityCollection.find(filter).first();

            if(activityRecord ==null){
                //create new activity record
                Gson gson = new Gson();
                String json = gson.toJson(activity);
                Document doc = Document.parse(json);
                InsertOneResult result= activityCollection.insertOne(doc);
                System.out.println(String.format("Inserted new record, result=%s", result.getInsertedId().toString()));
                map.put("activityID",result.getInsertedId().toString());
                return map;
            }else{
                //return this document information
                System.out.println(String.format("QueryResult=%s", activityRecord.toString()));
                map.put("activityID",activityRecord.get("_id",ObjectId.class).toString());
                return map;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
