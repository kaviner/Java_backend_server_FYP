package com.java_backend.apiserver.model;

import java.util.ArrayList;
import java.util.Date;

import org.bson.types.ObjectId;

public class MeasuredRecord {
    
    private ObjectId _id;       //MongoDB document ID
    private ObjectId userID;    //The userID in User collection, the owner of the record
    private String measureID;   //The measure ID
    
    private String deviceID;    //The deviceID for the device to pairing with PPG sensor and send the streaming data to server for analysis
    private String sensorID;    //The PPG sensor ID

    private ObjectId activityID;    //The activityID in Activity collection, the activity for the record
    private String activityName;    // The activity name
    private String category;        //The category of the record   
    private Date startDateTime;     //The start date time for this record
    private Date endDateTime;       //The end date time for this record
    
    private ArrayList<Sample> samples;      //The raw data sample
   
    private double avg_RRI_Value;           // The average RRI value
    private double avg_StressLevel_Value;   // The average stress level value
    private double avg_FatigueLevel_Value;  // The average fatigue level value
    private double avg_BPM_Value;           // The average fatigue level value
    
    private String avg_RRI_Label;            // The average RRI label
    private String avg_StressLevelLabel;     // The average stress level label
    private String avg_FatigueLevel_Label;   // The average fatigue level label
    private String avg_BPM_Label;            // The last RRI value

    private double last__RRI_Value;          // The last RRI value
    private double last__StressLevel_Value;  // The last stress level value
    private double last_FatigueLevel_Value;  // The last fatigue level value
    private double last_BPM_Value;           // The last bpm value
    
    private String last_RRI_Label;           // The last RRI label
    private String last_StressLevelLabel;    // The last stress level label 
    private String last_FatigueLevel_Label;  // The last fatigue level label 
    private String last_BPM_Label;           // The last bpm label

    private double last__RRI_Value;          // The last RRI value
    private double last__StressLevel_Value;  // The last stress level value
    private double last_FatigueLevel_Value;  // The last fatigue level value
    private double last_BPM_Value;           // The last bpm value
    
    private String last_RRI_Label;           // The last RRI label
    private String last_StressLevelLabel;    // The last stress level label 
    private String last_FatigueLevel_Label;  // The last fatigue level label 
    private String last_BPM_Label;           // The last bpm label
    
}
