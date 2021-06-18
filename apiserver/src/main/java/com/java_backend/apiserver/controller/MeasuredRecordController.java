package com.java_backend.apiserver.controller;

import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordFilter;
import com.java_backend.apiserver.model.MeasuredRecord.MeasuredRecordModel;
import com.java_backend.apiserver.mongo_operation.MeasuredRecordService;
import com.java_backend.apiserver.util.RequestVerifyUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasuredRecordController {
    @PostMapping(value = "/addMeasuredRecord", consumes = "application/json", produces = "text/plain")
    public String addMeasurRecord(@RequestBody MeasuredRecordModel measuredRecord) {
        RequestVerifyUtil.printRequestBody("/addMeasuredRecord", measuredRecord.toString());
        MeasuredRecordService measuredRecordService = new MeasuredRecordService();
        String result = measuredRecordService.addMeasurRecord(measuredRecord);
        return result;
    }


    @PostMapping(value = "/getMeasuredRecordListByOwner_FieldName", consumes = "application/json", produces = "application/json")
    public String getMeasuredRecordListByOwner_FieldName(@RequestBody MeasuredRecordFilter measuredRecordFilter) {
        RequestVerifyUtil.printRequestBody("/getMeasuredRecordListByOwner_FieldName", measuredRecordFilter.toString());
        MeasuredRecordService measuredRecordService = new MeasuredRecordService();
        String result = measuredRecordService.getMeasuredRecordList(measuredRecordFilter);
        return result;
    }




    // @PostMapping(
    // value = "/getMonitoringRelationshipForTarget", consumes = "text/plain",
    // produces = "application/json")
    // public String getMonitoringRelationshipForTarget(@RequestBody String
    // targetUserId) {
    // RequestVerifyUtil.printRequestBody("/getMonitoringRelationshipForTarget",targetUserId);
    // MonitoringRelationshipService scheudleManager = new
    // MonitoringRelationshipService();
    // String
    // result=scheudleManager.getMonitoringRelationshipForTarget(targetUserId);
    // return result;
    // }

    // @DeleteMapping(
    // value = "/deleteMonitoringRelationshipByHostOrTarget", consumes =
    // "application/json", produces = "text/plain")
    // public String deleteMonitoringRelationshipByHostOrTarget(@RequestBody
    // MonitoringRelationship_1To1 relationship) {
    // RequestVerifyUtil.printRequestBody("/deleteMonitoringRelationshipByHostOrTarge",relationship.toString());
    // MonitoringRelationshipService scheudleManager = new
    // MonitoringRelationshipService();
    // String
    // result=scheudleManager.deleteMonitoringRelationshipByHostOrTarget(relationship);
    // return result;
    // }
}
