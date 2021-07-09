package com.java_backend.apiserver.mongo_operation;

import java.util.HashMap;
import java.util.List;

import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.ne;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.max;
import static com.mongodb.client.model.Accumulators.min;

import com.mongodb.client.AggregateIterable;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Aggregates.sort;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class StatisticService {
    private ApplicationContext context;
    private MongoTemplate mongo;
    private String databaseName;
    private MongoDatabase db;
    private MongoCollection<Document> measuredRecordCollection, statisticCollection, userCollection;
    private MongoClient mongoClient;
    private MongoTemplate mongoTemplate;

    public StatisticService() {
        try {
            context = new AnnotationConfigApplicationContext(MongoConfig.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mongoClient = context.getBean(MongoClient.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        databaseName = context.getBean(String.class);
        db = mongoClient.getDatabase(databaseName);
        measuredRecordCollection = db.getCollection("MeasuredRecord");
        statisticCollection = db.getCollection("Statistic");
        userCollection = db.getCollection("User");
    }

    public HashMap<String, String> getBPMStatistic(String userId) {
        HashMap<String, String> response = new HashMap<String, String>();
        try {
            Document result = measuredRecordCollection
                    .aggregate(Arrays.asList(match(and(eq("userID", userId), ne("lowest_BPM_Value", 0L))),
                            group("$userID", max("max", "$highest_BPM_Value"), min("min", "$lowest_BPM_Value"),
                                    avg("avg", "$avg_BPM_Value"))))
                    .first();
            if(result==null){
                response.put("result","No record");
            }else{
                response.put("result", result.toJson().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.put("result", "Cannot query statistic record");
        }
        return response;
    }

    public HashMap<String, String> getHighestAndLowestStressLevelActivity(String userID) {
        HashMap<String, String> response = new HashMap<String, String>();
        try {
            AggregateIterable<Document> highestStressLevel_ActivityList = measuredRecordCollection
                    .aggregate(Arrays.asList(match(and(eq("userID", userID), ne("avg_StressLevel_Value", 0L))),
                            sort(descending("avg_StressLevel_Value")), project(include("activityName"))));
            MongoCursor<Document> iterator = highestStressLevel_ActivityList.iterator();
            JSONArray jArray = new JSONArray();
            while (iterator.hasNext()) {
                Document next = iterator.next();
                jArray.put(next.get("activityName"));
            }
            response.put("highestStressLevel_ActivityList", jArray.toString());

            AggregateIterable<Document> lowestStressLevel_ActivityList = measuredRecordCollection
                    .aggregate(Arrays.asList(match(and(eq("userID", userID), ne("avg_StressLevel_Value", 0L))),
                            sort(ascending("avg_StressLevel_Value")), project(include("activityName"))));

            MongoCursor<Document> iterator2 = lowestStressLevel_ActivityList.iterator();
            JSONArray jArray2 = new JSONArray();
            while (iterator2.hasNext()) {
                Document next = iterator2.next();
                jArray2.put(next.get("activityName"));
            }

            response.put("lowestStressLevel_ActivityList", jArray2.toString());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            response.put("result", e.getMessage());
        }
        return response;
    }

    public HashMap<String, String> getRestingAvgHRAndPPI(String userID) {
        HashMap<String, String> response = new HashMap<String,String>();
        try{
            Bson filter = eq("_id", new ObjectId(userID));
            
            Document result = userCollection.find(filter).projection(Projections.include("avgRestingAvgHRArray","avgRestingAvgPPIArray")).first();
            List<Document> restingAvgHRList=result.getList("avgRestingAvgHRArray", Document.class);
            JSONArray restingAvgHRArray = new JSONArray();
            for(Document record : restingAvgHRList){
                JSONObject jObject = new JSONObject();
                jObject.put("avgRestingAvgHR", record.getDouble("avgRestingAvgHR"));
                jObject.put("timestamp", record.getDate("timestamp"));
                restingAvgHRArray.put(jObject);
            }
            response.put("restingAvgHRArray", restingAvgHRArray.toString());

            List<Document> restingAvgPPIList=result.getList("avgRestingAvgPPIArray", Document.class);
            JSONArray restingAvgPPIArray = new JSONArray();
            for(Document record : restingAvgPPIList){
                JSONObject jObject = new JSONObject();
                jObject.put("avgRestingAvgPPI", record.getDouble("avgRestingAvgPPI"));
                jObject.put("timestamp", record.getDate("timestamp"));
                restingAvgPPIArray.put(jObject);
            }
            response.put("restingAvgPPIArray", restingAvgPPIArray.toString());
        }catch(Exception e){
            e.printStackTrace();
            response.put("result", e.getMessage());
        }
        
        return response;
    }

}
