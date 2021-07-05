package com.java_backend.apiserver.controller;

import java.util.HashMap;

import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordFilter;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordModel;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredResult;
import com.java_backend.apiserver.model.MeasuredRecord.PPG_SignalSet;
import com.java_backend.apiserver.mongo_operation.MeasuredRecordService;
import com.java_backend.apiserver.util.RequestVerifyUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasuredRecordController {
    @PostMapping(value = "/addMeasuredRecord", consumes = "application/json", produces = "application/json")
    public HashMap<String,String>  addMeasurRecord(@RequestBody MeasuredRecordModel measuredRecord) {
        RequestVerifyUtil.printRequestBody("/addMeasuredRecord", measuredRecord.toString());
        MeasuredRecordService measuredRecordService = new MeasuredRecordService();
        HashMap<String,String>  result = measuredRecordService.addMeasurRecord(measuredRecord);
        return result;
    }

    @PostMapping(value = "/getMeasuredRecordListByOwner_FieldName", consumes = "application/json", produces = "application/json")
    public String getMeasuredRecordListByOwner_FieldName(@RequestBody MeasuredRecordFilter measuredRecordFilter) {
        RequestVerifyUtil.printRequestBody("/getMeasuredRecordListByOwner_FieldName", measuredRecordFilter.toString());
        MeasuredRecordService measuredRecordService = new MeasuredRecordService();
        String result = measuredRecordService.getMeasuredRecordList(measuredRecordFilter);
        return result;
    }

    @PostMapping(value = "/pushNewPPGSignalRecordTo_MeasuredRecord", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> pushNewPPGSignalRecordTo_MeasuredRecord(@RequestBody PPG_SignalSet signalSet) {
        RequestVerifyUtil.printRequestBody("/pushNewPPGSignalRecordTo_MeasuredRecord",  String.valueOf(signalSet.getPpgSignalSet().size()));
        MeasuredRecordService measuredRecordService = new MeasuredRecordService();
        HashMap<String,String> result = measuredRecordService.pushNewPPGSignalRecordTo_MeasuredRecord(signalSet.getPpgSignalSet());
        return result;
    }

    @PostMapping(value = "/pushNewMeasuredResult_To_MeasuredRecord", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> pushNewMeasuredResult_To_MeasuredRecord(@RequestBody MeasuredResult measuredResult) {
        RequestVerifyUtil.printRequestBody("/pushNewMeasuredResult_To_MeasuredRecord",  measuredResult.toString());
        MeasuredRecordService measuredRecordService = new MeasuredRecordService();
        HashMap<String,String> result = measuredRecordService.pushNewMeasuredResult_To_MeasuredRecord(measuredResult);
        return result;
    }



}
