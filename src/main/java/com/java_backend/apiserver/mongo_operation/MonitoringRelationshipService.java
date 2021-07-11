package com.java_backend.apiserver.mongo_operation;

import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
import com.java_backend.apiserver.model.MonitoringRelationship_1To1;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UnwindOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;

import static com.mongodb.client.model.Filters.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MonitoringRelationshipService {
    private ApplicationContext context;
    private MonitoringRelationship_1To1 relatonship;
    private MongoTemplate mongo;
    private String databaseName;
    private MongoDatabase db;
    private MongoCollection<Document> monitoringRelationshipCollection,measuredRecordCollection;
    private MongoClient mongoClient;
    private MongoTemplate mongoTemplate;

    public MonitoringRelationshipService() {
        try {
            context = new AnnotationConfigApplicationContext(MongoConfig.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mongoClient = context.getBean(MongoClient.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        databaseName = context.getBean(String.class);
        db = mongoClient.getDatabase(databaseName);
        monitoringRelationshipCollection = db.getCollection("MonitoringRelationship");
        measuredRecordCollection = db.getCollection("MeasuredRecord");
    }

    public MonitoringRelationshipService(MonitoringRelationship_1To1 relatonship) {
        this.relatonship = relatonship;
        try {
            context = new AnnotationConfigApplicationContext(MongoConfig.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mongoClient = context.getBean(MongoClient.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        databaseName = context.getBean(String.class);
        db = mongoClient.getDatabase(databaseName);
        monitoringRelationshipCollection = db.getCollection("MonitoringRelationship");
        measuredRecordCollection = db.getCollection("MeasuredRecord");
    }

    public HashMap<String, String> initMonitoringRelationship(Map<String, String> request) {
        HashMap<String, String> response = new HashMap<String, String>();
        
        try{
            Bson filter = eq("targetID", request.get("targetID"));
            Bson updates = Updates.set("pairCode", request.get("pairCode"));
            Document doc = monitoringRelationshipCollection.findOneAndUpdate(filter,updates);
            if (doc != null) {
                response.put("result", "Exist relationship");
            }else{
                Document newRecord = new Document();
                newRecord.append("targetID", request.get("targetID"));
                newRecord.append("pairCode", request.get("pairCode"));
                InsertOneResult result = monitoringRelationshipCollection.insertOne(newRecord);
                response.put("result", result.toString());
            }
        }catch(Exception e){
            e.printStackTrace();
            response.put("result", e.getMessage());
        }
        return response;
    }

    public HashMap<String, String> verifyCode(Map<String, String> request) {
        HashMap<String, String> response = new HashMap<String, String>();
        
        try{
            Bson filter = eq("pairCode", request.get("pairCode"));
            Bson updates = Updates.set("monitorID", request.get("monitorID"));
            Document doc = monitoringRelationshipCollection.findOneAndUpdate(filter,updates);
            if (doc == null) {
                response.put("result", "false");
            }else{
                response.put("result", doc.get("targetID").toString());
            }
        }catch(Exception e){
            e.printStackTrace();
            response.put("result", e.getMessage());
        }
        return response;
    }

    public HashMap<String,String> getLastMeasuredRecord(Map<String,String> request){
        HashMap<String,String> response = new HashMap<String,String>();
        try{

        boolean isValid =isValidRelationship(request);
        if(isValid){
            Bson filter = eq("userID", request.get("targetID"));
            Document result = measuredRecordCollection.find(filter).sort(new BasicDBObject("_id",-1)).first();
            response.put("result", result.toJson().toString());
        }else{
            response.put("error","invalid relationship");
        }

        }catch(Exception e){
            response.put("error", e.getMessage());
        }
        return response;
    }
    private boolean isValidRelationship(Map<String,String> request){
        Bson filter = and(eq("targetID", request.get("targetID")),eq("pairCode", request.get("pairCode")));
        Document result =monitoringRelationshipCollection.find(filter).first();
        if(result==null){
            return false;
        }else{
            return true;
        }
    }

    // public String addMonitoringRelationship(MonitoringRelationship_1To1 relationship) {
    //     String hostId = relationship.getHost_ID();
    //     String targetId = relationship.getTargetID();
    //     String pairCode = relationship.getPairCode();
    //     relationship.setRelationshipStartDate(new Date());
    //     System.out.println("Going to add monitoring relationship for " + relationship.toString());

    //     // Check host id exist or not in MonitoringRelationship collection
    //     Bson filter = eq("host_ID", hostId);
    //     JSONArray results = new JSONArray();
    //     try {
    //         Document document = null;
    //         MongoCursor<Document> cursor = monitoringRelationshipCollection.find(filter).iterator();
    //         while (cursor.hasNext()) {
    //             document = cursor.next();
    //             results.put(document.toJson());
    //         }
    //         System.out.println("The Exisiting hostID =" + results.toString());
    //         UpdateResult updateResult = null;
    //         if (document == null) {
    //             // create new document for this hostId
    //             System.out.println("hostId cannot find, create new document for this hostId");
    //             Document doc = new Document();
    //             doc.put("host_ID", hostId);
    //             List<BasicDBObject> target_ID_List = new ArrayList<>();
    //             BasicDBObject element = new BasicDBObject();
    //             element.append("targetID", targetId);
    //             element.append("pairCode", pairCode);
    //             element.append("status", "pairing");
    //             element.append("monitorStartDate", relationship.getRelationshipStartDate());
    //             target_ID_List.add(element);
    //             doc.put("target_ID_List", target_ID_List);
    //             monitoringRelationshipCollection.insertOne(doc);
    //         } else {
    //             System.out.println(String.format("QueryResult=%s", results.toString()));
    //             // push new target in the target_ID_List array in the MongoDB
    //             List<BasicDBObject> target_ID_List = new ArrayList<>();
    //             BasicDBObject element = new BasicDBObject();
    //             element.append("targetID", targetId);
    //             element.append("pairCode", pairCode);
    //             element.append("status", "pairing");
    //             element.append("monitorStartDate", relationship.getRelationshipStartDate());
    //             target_ID_List.add(element);
    //             DBObject listItem = new BasicDBObject("target_ID_List", element);
    //             Bson updateQuery = new BasicDBObject("$push", listItem);
    //             updateResult = monitoringRelationshipCollection.updateOne(filter, updateQuery);

    //         }
    //         if (updateResult != null)
    //             return String.format("added MonitoringRelationship hostId=%s targetId=%s updateResult=%s", hostId,
    //                     targetId, updateResult.getModifiedCount());
    //         else
    //             return String.format("added MonitoringRelationship hostId=%s targetId=%s ", hostId, targetId);
    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //         return ex.getMessage();
    //     }
    // }

    // public String getMonitoringRelationshipForHost(String hostId) {
    //     Bson filter = eq("host_ID", hostId);

    //     JSONArray results = new JSONArray();
    //     try {
    //         MongoCursor<Document> cursor = monitoringRelationshipCollection.find(filter).iterator();
    //         while (cursor.hasNext()) {
    //             Document document = cursor.next();
    //             results.put(document.toJson());
    //         }
    //         System.out.println(String.format("QueryResult=%s", results.toString()));
    //         return results.toString();
    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //     }
    //     return null;
    // }

    // public String getMonitoringRelationshipForTarget(String targetUserId) {
    //     // Bson filter = and(elemMatch("target_ID_List", eq("targetID", targetUserId)),
    //     // eq("target_ID_List.$", 1));

    //     JSONArray results = new JSONArray();
    //     try {
    //         // monitoringRelationshipCollection.aggregate(pipeline);
    //         List<String> projectionList = new ArrayList<String>();
    //         projectionList.add("_id");
    //         projectionList.add("host_ID");
    //         MongoCursor<Document> cursor = monitoringRelationshipCollection
    //                 .aggregate(Arrays.asList(Aggregates.match(eq("target_ID_List.targetID", targetUserId)),
    //                         Aggregates.lookup("MonitoringRelationship", "target_ID_List.targetID", "_id", "parent"),
    //                         Aggregates.unwind("$parent", new UnwindOptions().preserveNullAndEmptyArrays(true)),
    //                         Aggregates.project(Projections.include(projectionList))))
    //                 .iterator();
    //         while (cursor.hasNext()) {
    //             Document document = cursor.next();
    //             results.put(document.toJson());
    //         }
    //         System.out.println(String.format("QueryResult=%s", results.toString()));
    //         return results.toString();
    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //     }
    //     return null;
    // }

    // public String deleteMonitoringRelationshipByHostOrTarget(MonitoringRelationship_1To1 relationship) {
    //     try {
    //         Bson filter = and(eq("host_ID", relationship.getHost_ID()),
    //                 elemMatch("target_ID_List", eq("targetID", relationship.getTargetID())));
    //         monitoringRelationshipCollection.findOneAndUpdate(filter,
    //                 Updates.pull("target_ID_List", eq("targetID", relationship.getTargetID())));
    //         return String.format("Deleted monitoring relationship--> {\"host_ID\":\"%s\",\"target_ID\":\"%s\"}",
    //                 relationship.getHost_ID(), relationship.getTargetID());
    //     } catch (Exception ex) {
    //         ex.printStackTrace();
    //         System.out.println(ex.getMessage());
    //     }
    //     return null;
    // }

}
