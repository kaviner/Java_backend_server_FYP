package com.java_backend.apiserver.mongo_operation;

import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordModel;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredResult;
import com.java_backend.apiserver.model.MeasuredRecord.PPG_Signal;
import com.java_backend.apiserver.model.MeasuredRecord.PPG_SignalSet;
import com.java_backend.apiserver.util.DateUtil;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordFilter;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.PushOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MeasuredRecordService {
    private ApplicationContext context;
    private MeasuredRecordModel measuredRecord;
    private MongoTemplate mongo;
    private String databaseName;
    private MongoDatabase db;
    private MongoCollection<Document> measuredRecordCollection;
    private MongoClient mongoClient;
    private MongoTemplate mongoTemplate;

    public MeasuredRecordService() {
        try {
            context = new AnnotationConfigApplicationContext(MongoConfig.class);
            CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            mongoClient = context.getBean(MongoClient.class);
            mongoTemplate = context.getBean(MongoTemplate.class);
            databaseName = context.getBean(String.class);
            db = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
            measuredRecordCollection = db.getCollection("MeasuredRecord");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public MeasuredRecordService(MeasuredRecordModel measuredRecord) {
        this.measuredRecord = measuredRecord;
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
    }
    public HashMap<String,String>  addMeasurRecord(MeasuredRecordModel measuredRecord) {
        HashMap<String,String> map = new HashMap<String,String>();
        try {
            Gson gson = new Gson();
            String json = gson.toJson(measuredRecord);
            Document doc = Document.parse(json);
            doc.replace("startDateTime", measuredRecord.getStartDateTime()); // fix the wrong simple date format during
                                                                             // transfer
            doc.replace("endDateTime", measuredRecord.getEndDateTime()); // fix the wrong simple date format during
                                                                         // transfer
            measuredRecordCollection.insertOne(doc);
            String result = String.format("Inserted measured record for measureID = %s", measuredRecord.getMeasureID());
            System.out.println(result);
            map.put("result", measuredRecord.getMeasureID());
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            map.put("error", ex.getMessage());
            return map;
        }
    }

    public String getMeasuredRecordList(MeasuredRecordFilter measuredRecordFilter) {
        String ownerID = measuredRecordFilter.getRecordOwner();
        String fieldName = measuredRecordFilter.getSortByFieldName();
        String startDateString = measuredRecordFilter.getStartDateTime();
        String endDateString = measuredRecordFilter.getEndDateTime();
        Boolean isAscending = measuredRecordFilter.getIsAscending();

        Date startDate;
        Date startEndDate;
        startDate = DateUtil.stringToDate(startDateString);
        
        startEndDate = DateUtil.stringToDate(endDateString);
        System.out.println(startDate);

        Bson filter = and(eq("userID", ownerID), 
            and(gte("startDateTime", startDate),lte("startDateTime", startEndDate))
        );
        // Bson filter= eq("userID", ownerID);
        JSONArray results = new JSONArray();
        try {
            MongoCursor<Document> cursor=null;
            if(fieldName==null)
                cursor = measuredRecordCollection.find(filter).iterator();
            else if(isAscending==true){
                cursor = measuredRecordCollection.find(filter).sort(new BasicDBObject(fieldName,1)).iterator();
            }else if(isAscending==false){
                cursor = measuredRecordCollection.find(filter).sort(new BasicDBObject(fieldName,-1)).iterator();
            }
            while (cursor.hasNext()) {
                Document document = cursor.next();
                results.put(document.toJson());
            }
            System.out.println(String.format("QueryResult=%s", results.toString()));
            return results.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public HashMap<String,String>  pushNewPPGSignalRecordTo_MeasuredRecord(List<PPG_Signal> list) {
        HashMap<String, String> map = new HashMap<>();
        try {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            String measuredID = list.get(0).getMeasureId();
            System.out.println(measuredID);
            Bson filter = eq("measureID", measuredID);
            //first record
            Bson updates;
            //updates= Updates.addToSet("ppgSignalSet", list);

            //not first record
            updates= Updates.push("ppgSignalSet", list);

            UpdateResult pushSignalToMongoDBArray = measuredRecordCollection.updateOne(filter,updates);
            String result = String.format("Matched count = %s, Modified count = %s",pushSignalToMongoDBArray.getMatchedCount(),pushSignalToMongoDBArray.getModifiedCount());
            System.out.println(result);
            map.put("result", result);
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            map.put("result", ex.getMessage());
            return map;
        }
    }

    public HashMap<String,String>  pushNewMeasuredResult_To_MeasuredRecord(MeasuredResult measuredResult) {
        HashMap<String, String> map = new HashMap<>();
        try {
            String measuredID = measuredResult.getMeasureID();
            System.out.println(measuredID);
            Bson filter = eq("measureID", measuredID);
            //first record
            Bson updates;
            //updates= Updates.addToSet("ppgSignalSet", list);

            //not first record
            updates= Updates.push("measuredResult", measuredResult);

            UpdateResult pushSignalToMongoDBArray = measuredRecordCollection.updateOne(filter,updates);
            String result = String.format("Matched count = %s, Modified count = %s",pushSignalToMongoDBArray.getMatchedCount(),pushSignalToMongoDBArray.getModifiedCount());
            System.out.println(result);
            map.put("result", result);
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            map.put("result", ex.getMessage());
            return map;
        }
    }

}