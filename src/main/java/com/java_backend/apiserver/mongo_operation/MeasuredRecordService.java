package com.java_backend.apiserver.mongo_operation;

import com.java_backend.apiserver.config.mongodb_config.MongoConfig;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordModel;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredResult;
import com.java_backend.apiserver.model.MeasuredRecord.PPG_Signal;
import com.java_backend.apiserver.model.MeasuredRecord.PPG_SignalSet;
import com.java_backend.apiserver.util.DateUtil;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordFilter;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Aggregates.addFields;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Aggregates.project;
import com.mongodb.client.model.Field;
import java.util.concurrent.TimeUnit;

public class MeasuredRecordService {
    private ApplicationContext context;
    private MeasuredRecordModel measuredRecord;
    private MongoTemplate mongo;
    private String databaseName;
    private MongoDatabase db;
    private MongoCollection<Document> measuredRecordCollection, userCollection;
    private MongoClient mongoClient;
    private MongoTemplate mongoTemplate;

    public MeasuredRecordService() {
        try {
            context = new AnnotationConfigApplicationContext(MongoConfig.class);
            CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries
                            .fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            mongoClient = context.getBean(MongoClient.class);
            mongoTemplate = context.getBean(MongoTemplate.class);
            databaseName = context.getBean(String.class);
            db = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
            measuredRecordCollection = db.getCollection("MeasuredRecord");
            userCollection = db.getCollection("User");
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

    public HashMap<String, String> addMeasurRecord(MeasuredRecordModel measuredRecord) {
        HashMap<String, String> map = new HashMap<String, String>();
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
                and(gte("startDateTime", startDate), lte("startDateTime", startEndDate)));
        // Bson filter= eq("userID", ownerID);
        JSONArray results = new JSONArray();
        try {
            MongoCursor<Document> cursor = null;
            if (fieldName == null)
                cursor = measuredRecordCollection.find(filter).iterator();
            else if (isAscending == true) {
                cursor = measuredRecordCollection.find(filter).sort(new BasicDBObject(fieldName, 1)).iterator();
            } else if (isAscending == false) {
                cursor = measuredRecordCollection.find(filter).sort(new BasicDBObject(fieldName, -1)).iterator();
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

    public HashMap<String, String> pushNewPPGSignalRecordTo_MeasuredRecord(List<PPG_Signal> list) {
        HashMap<String, String> map = new HashMap<>();
        try {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            String measuredID = list.get(0).getMeasureId();
            System.out.println(measuredID);
            Bson filter = eq("measureID", measuredID);
            // first record
            Bson updates;
            // updates= Updates.addToSet("ppgSignalSet", list);

            // not first record
            updates = Updates.push("ppgSignalSet", list);

            UpdateResult pushSignalToMongoDBArray = measuredRecordCollection.updateOne(filter, updates);
            String result = String.format("Matched count = %s, Modified count = %s",
                    pushSignalToMongoDBArray.getMatchedCount(), pushSignalToMongoDBArray.getModifiedCount());
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

    public HashMap<String, String> pushNewMeasuredResult_To_MeasuredRecord(MeasuredResult measuredResult) {
        HashMap<String, String> map = new HashMap<>();
        try {
            String measuredID = measuredResult.getMeasureID();
            System.out.println(measuredID);
            Bson filter = eq("measureID", measuredID);
            // first record
            Bson updates;
            updates = Updates.push("measuredResult", measuredResult);

            UpdateResult pushSignalToMongoDBArray = measuredRecordCollection.updateOne(filter, updates);
            String result = String.format("Matched count = %s, Modified count = %s",
                    pushSignalToMongoDBArray.getMatchedCount(), pushSignalToMongoDBArray.getModifiedCount());
            System.out.println(result);
            map.put("result", result);
            map = updateOverallResultForThisMeasure(measuredID);
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            map.put("result", ex.getMessage());
            return map;
        }
    }

    public HashMap<String, String> updateOverallResultForThisMeasure(String measureId) {
        //Aggregate stat data 
        Document result = measuredRecordCollection.aggregate(Arrays.asList(
                match(eq("measureID", measureId)),
                addFields(new Field("lowest_PPI_Value", new Document("$min", "$measuredResult.avg_overall_ppi")),
                        new Field("highest_PPI_Value", new Document("$max", "$measuredResult.avg_overall_ppi")),
                        new Field("avg_PPI_Value", new Document("$avg", "$measuredResult.avg_overall_ppi")),
                        new Field("lowest_BPM_Value", new Document("$min", "$measuredResult.avg_overall_bpm")),
                        new Field("highest_BPM_Value", new Document("$max", "$measuredResult.avg_overall_bpm")),
                        new Field("avg_BPM_Value", new Document("$avg", "$measuredResult.avg_overall_bpm"))),
                project(include("lowest_PPI_Value", "highest_PPI_Value", "avg_PPI_Value", "lowest_BPM_Value",
                        "highest_BPM_Value", "avg_BPM_Value"))))
                .first();
        System.out.println(result.toString());

        Bson filters = eq("measureID", measureId);
        BasicDBObject updates = new BasicDBObject();
        BasicDBObject set = new BasicDBObject("$set", updates);
        updates.put("lowest_PPI_Value", result.get("lowest_PPI_Value"));
        updates.put("highest_PPI_Value", result.get("highest_PPI_Value"));
        updates.put("avg_PPI_Value", result.get("avg_PPI_Value"));
        updates.put("lowest_BPM_Value", result.get("lowest_BPM_Value"));
        updates.put("lowest_PPI_Value", result.get("lowest_PPI_Value"));
        updates.put("highest_BPM_Value", result.get("highest_BPM_Value"));
        updates.put("avg_BPM_Value", result.get("avg_BPM_Value"));
        UpdateResult updateResult = measuredRecordCollection.updateOne(filters, set);
        HashMap<String, String> map = new HashMap<String, String>();

        String userID = measureId.substring(0, 24);
        Document userResult = userCollection.find(eq("_id", new ObjectId(userID))).first();
        double restingAvgHeartRate = (double) userResult.get("restingAvgHeartRate");
        double restingAvgPPI = (double) userResult.get("restingAvgPPI");
        System.out.println(String.format("restingAvgHeartRate=%s, restingAvgPPI=%s", restingAvgHeartRate,restingAvgPPI));
        double AvgPercentageLargerThanRestingBPM = (((double)result.get("avg_BPM_Value")) / restingAvgHeartRate)-1;
        /*
        resting avg bpm * (1+x%) = measure avg bpm
        69.35714285714286 *(1+ x%) = 82.14285714285714
        82.14285714285714 / 69.35714285714286 = 1 + x%
        x% = 82.14285714285714 / 69.35714285714286 -1
        */
        
        double AvgPercentageLargerThanRestingPPI = 1-(((double)result.get("avg_PPI_Value")) / restingAvgPPI);
        /*
        measureAvgPPI = restingAvgPPI*(1-x%)
        measureAvgPPI/restingAvgPPI = 1-x%
        x% = 1-measureAvgPPI/restingAvgPPI
        */
        System.out.println(String.format("AvgPercentageLargerThanRestingBPM=%s, AvgPercentageSmallerThanRestingPPI=%s", AvgPercentageLargerThanRestingBPM,AvgPercentageLargerThanRestingPPI));


        map.put("result", updateResult.toString());
        return map;
    }

    public HashMap<String, String> pushNewRestingData_To_Profile(MeasuredResult measuredResult) {
        HashMap<String, String> map = new HashMap<>();
        try {
            String measuredID = measuredResult.getMeasureID();
            String userID = measuredID.substring(0, 24);
            System.out.println(measuredID);
            Bson filter = eq("_id", new ObjectId(userID));

            Bson updatesPPIArray;
            updatesPPIArray = Updates.push("avgRestingAvgPPIArray", measuredResult.getAvg_overall_ppi());

            Bson updatesHRArray;
            updatesHRArray = Updates.push("avgRestingAvgHRArray", measuredResult.getAvg_overall_bpm());

            UpdateResult pushAvgHRToProfile = userCollection.updateOne(filter, updatesPPIArray);
            String result = String.format("Matched count = %s, Modified count = %s",
                    pushAvgHRToProfile.getMatchedCount(), pushAvgHRToProfile.getModifiedCount());
            System.out.println(result);
            map.put("result", result);

            UpdateResult pushAvgPPIToProfile = userCollection.updateOne(filter, updatesHRArray);
            String result2 = String.format("Matched count = %s, Modified count = %s",
                    pushAvgPPIToProfile.getMatchedCount(), pushAvgPPIToProfile.getModifiedCount());
            System.out.println(result);
            map.put("result", result2);
            map = updateOverallResultForThisProfile(userID);
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            map.put("result", ex.getMessage());
            return map;
        }
    }

    public HashMap<String, String> updateOverallResultForThisProfile(String profileID) {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            Document result = userCollection
                    .aggregate(Arrays.asList(match(eq("_id", new ObjectId(profileID))),
                            addFields(new Field("restingAvgHeartRate", new Document("$avg", "$avgRestingAvgHRArray")),
                                    new Field("restingAvgPPI", new Document("$avg", "$avgRestingAvgPPIArray"))),
                            project(include("restingAvgHeartRate", "restingAvgPPI"))))
                    .first();
            System.out.println(result.toString());

            Bson filters = eq("_id", new ObjectId(profileID));
            BasicDBObject updates = new BasicDBObject();
            BasicDBObject set = new BasicDBObject("$set", updates);
            updates.put("restingAvgHeartRate", result.get("restingAvgHeartRate"));
            updates.put("restingAvgPPI", result.get("restingAvgPPI"));
            System.out.println(filters.toString());
            UpdateResult updateResult = userCollection.updateOne(filters, set);
            System.out.println(updateResult.toString());
            map.put("result", updateResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", e.getMessage());
        }
        return map;
    }
}