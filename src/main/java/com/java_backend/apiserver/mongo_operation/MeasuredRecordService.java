package com.java_backend.apiserver.mongo_operation;

import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordModel;
import com.java_backend.apiserver.util.DateUtil;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordFilter;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        mongoClient = context.getBean(MongoClient.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        databaseName = context.getBean(String.class);
        db = mongoClient.getDatabase(databaseName);
        measuredRecordCollection = db.getCollection("MeasuredRecord");
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

    public String addMeasurRecord(MeasuredRecordModel measuredRecord) {
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
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return ex.getMessage();
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
}