package com.java_backend.apiserver.controller;

import com.java_backend.apiserver.model.MeasuredRecord;
import com.java_backend.apiserver.model.MonitoringRelationship_1To1;
import com.java_backend.apiserver.mongo_operation.MeasuredRecordService;
import com.java_backend.apiserver.mongo_operation.MonitoringRelationshipService;
import com.java_backend.apiserver.util.RequestVerifyUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasuredRecordController {
    @PostMapping(value = "/addMeasurRecord", consumes = "application/json", produces = "text/plain")
    public String addMeasurRecord(@RequestBody MeasuredRecord measuredRecord) {
        RequestVerifyUtil.printRequestBody("/addMeasurRecord", measuredRecord.toString());
        MeasuredRecordService measuredRecordService = new MeasuredRecordService();
        String result = measuredRecordService.addMeasurRecord(measuredRecord);
        return result;

    }
    // @PostMapping(
    // value = "/getMonitoringRelationshipForHost", consumes = "text/plain",
    // produces = "application/json")
    // public String getMonitoringRelationshipForHost(@RequestBody String
    // hostUserId) {
    // RequestVerifyUtil.printRequestBody("/getMonitoringRelationshipForHost",hostUserId);
    // MonitoringRelationshipService monitoringRelationshipManager = new
    // MonitoringRelationshipService();
    // String
    // result=monitoringRelationshipManager.getMonitoringRelationshipForHost(hostUserId);
    // return result;
    // }

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
