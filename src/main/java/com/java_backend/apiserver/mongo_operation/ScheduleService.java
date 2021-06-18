package com.java_backend.apiserver.mongo_operation;

import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
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

    public String addNewSchedule(){
        try{
            Document doc = new Document();
            doc.put("ownerIdInUserCollection",  new ObjectId(schedule.getOwnerInUserCollection()));
            doc.put("activityIdInActivityCollection", new ObjectId(schedule.getActivityIdInActivityCollection()));
            doc.put("startDateTime", schedule.getStartDateTime());
            doc.put("endDateTime", schedule.getEndDateTime());
            doc.put("isAlert", schedule.getIsAlert());
            doc.put("alertValue",schedule.getAlertValue());
            doc.put("status", "scheduled");
            scheduleCollection.insertOne(doc);
            System.out.println("Added new schedule");
        }catch(Exception ex){
            ex.printStackTrace();
            return ex.getMessage();
        }
        return String.format("Added new Schedule");
    }

    public String updateSchedule(Schedule schedule){
        try{
            Bson filter = eq("_id", new ObjectId(schedule.get_id()));
            Document doc = new Document();
            doc.put("ownerIdInUserCollection",  new ObjectId(schedule.getOwnerInUserCollection()));
            doc.put("activityIdInActivityCollection", new ObjectId(schedule.getActivityIdInActivityCollection()));
            doc.put("startDateTime", schedule.getStartDateTime());
            doc.put("endDateTime", schedule.getEndDateTime());
            doc.put("isAlert", schedule.getIsAlert());
            doc.put("alertValue",schedule.getAlertValue());
            doc.put("status", "scheduled");
            UpdateResult updateResult =scheduleCollection.replaceOne(filter, doc);
            return updateResult.toString();
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String deleteSchedule(String scheduleId){
        try{
            Bson filter = eq("_id", new ObjectId(scheduleId));
            scheduleCollection.deleteOne(filter);
            return "Deleted schedule: "+scheduleId;
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public String getScheduleInfoForOwner(String ownerId){
        Bson filter = eq("ownerIdInUserCollection", new ObjectId(ownerId));
        JSONArray results = new JSONArray();
        try{
            MongoCursor<Document> cursor=scheduleCollection.find(filter).iterator();
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
