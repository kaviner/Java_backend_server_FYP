package com.java_backend.apiserver.mongo_operation;

import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
import com.java_backend.apiserver.model.QueryModel;
import com.java_backend.apiserver.model.QueryModelForSchedule;
import com.java_backend.apiserver.model.Schedule;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import static com.mongodb.client.model.Filters.*;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

public class ScheduleService {
    private ApplicationContext context;
    private Schedule schedule;
    private MongoTemplate mongo;
    private String databaseName;
    private MongoDatabase db;
    private MongoCollection<Document> scheduleCollection;
    private MongoClient mongoClient;
    private MongoTemplate mongoTemplate;


    public ScheduleService(){
        try{
            context = new AnnotationConfigApplicationContext(MongoConfig.class);
          }catch(Exception ex){
              ex.printStackTrace();
          }
          mongoClient = context.getBean(MongoClient.class);
          mongoTemplate = context.getBean(MongoTemplate.class);
          databaseName = context.getBean(String.class);
          db=mongoClient.getDatabase(databaseName);
          scheduleCollection = db.getCollection("Schedule");
    }
    public ScheduleService(Schedule schedule) {
        this.schedule=schedule;
        try{
          context = new AnnotationConfigApplicationContext(MongoConfig.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        mongoClient = context.getBean(MongoClient.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        databaseName = context.getBean(String.class);
        db=mongoClient.getDatabase(databaseName);
        scheduleCollection = db.getCollection("Schedule");
    }

    public HashMap<String,String> addNewSchedule(){
        HashMap<String, String> map = new HashMap<>();
        try{
            Document doc = new Document();
            doc.put("ownerIdInUserCollection",  new ObjectId(schedule.getOwnerInUserCollection().toString()));
            doc.put("activityIdInActivityCollection", new ObjectId(schedule.getActivityIdInActivityCollection()));
            doc.put("activityName", schedule.getActivityName());
            doc.put("category", schedule.getCategory());
            doc.put("startDateTime", schedule.getStartDateTime());
            doc.put("endDateTime", schedule.getEndDateTime());
            doc.put("status", schedule.getStatus());
            InsertOneResult result =scheduleCollection.insertOne(doc);
            System.out.println("Added new schedule "+result.getInsertedId().asObjectId().getValue().toString());
            map.put("_id for inserted schedule", result.getInsertedId().asObjectId().getValue().toString());
        }catch(Exception ex){
            ex.printStackTrace();
            map.put("error", ex.getMessage());
            return map;
        }
        return map;
    }

    public HashMap<String,String> updateSchedule(Schedule schedule){
        HashMap<String,String> map = new HashMap<String,String>();

        try{
            Bson filter = eq("_id", new ObjectId(schedule.get_id().toString()));
            Document doc = new Document();
            doc.put("ownerIdInUserCollection",  new ObjectId(schedule.getOwnerInUserCollection()));
            doc.put("activityIdInActivityCollection", new ObjectId(schedule.getActivityIdInActivityCollection()));
            doc.put("activityName", schedule.getActivityName());
            doc.put("startDateTime", schedule.getStartDateTime());
            doc.put("endDateTime", schedule.getEndDateTime());
            doc.put("status", schedule.getStatus());
            doc.put("category", schedule.getCategory());
            UpdateResult updateResult =scheduleCollection.replaceOne(filter, doc);
            map.put("result", updateResult.toString());
            return map;
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            map.put("result", ex.getMessage());
            return map;
        }
    }

    public HashMap<String, String> deleteSchedule(Map<String, String> scheduleId){
        HashMap<String, String> map = new HashMap<>();
        try{
            System.out.println(scheduleId.get("_id").toString());
            Bson filter = eq("_id",new ObjectId(scheduleId.get("_id").toString()));
            // Bson filter = eq("_id",new ObjectId("6059994458b4857c101a5185"));
            DeleteResult result= scheduleCollection.deleteOne(filter);
            map.put("deletedResult", String.valueOf(result.getDeletedCount()));
            return map;
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            map.put("result", ex.getMessage());
            return map;
        }
    }

    public String getScheduleInfoForOwner(QueryModelForSchedule model){
        String ownerID = model.getUserID();
        Bson filter = eq("ownerIdInUserCollection", new ObjectId(ownerID));
        JSONArray results = new JSONArray();
        try{
            MongoCursor<Document> cursor=null;
            if(model.getStatus()!=null){
                filter = eq("status", model.getStatus());
                cursor=scheduleCollection.find(filter).sort(new BasicDBObject("startDateTime",1)).iterator();
            }else if(model.getSortByField()!=null){
                if(model.getSortOrder())
                    cursor=scheduleCollection.find(filter).sort(new BasicDBObject(model.getSortByField(),1)).iterator();
                else
                    cursor=scheduleCollection.find(filter).sort(new BasicDBObject(model.getSortByField(),-1)).iterator();
            }else{
                cursor=scheduleCollection.find(filter).sort(new BasicDBObject("startDateTime",1)).iterator();
            }
            while(cursor.hasNext()){
                Document document=cursor.next();
                results.put(document.toJson());
            }
            System.out.println(String.format("QueryResult=%s", results.toString()));
            return results.toString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
