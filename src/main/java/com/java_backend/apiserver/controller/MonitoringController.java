package com.java_backend.apiserver.controller;

import java.util.HashMap;
import java.util.Map;

import com.java_backend.apiserver.model.MonitoringRelationship_1To1;
import com.java_backend.apiserver.mongo_operation.MonitoringRelationshipService;
import com.java_backend.apiserver.util.RequestVerifyUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitoringController {

    @PostMapping(
      value = "/initMonitoringRelationship", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> initMonitoringRelationship(@RequestBody Map<String,String> request) {
        RequestVerifyUtil.printRequestBody("/initMonitoringRelationship",request.toString());
        MonitoringRelationshipService monitoringRelationshipManager = new MonitoringRelationshipService();
        HashMap<String,String> result=monitoringRelationshipManager.initMonitoringRelationship(request);
        return result;
    }

    @PostMapping(
      value = "/getLastMeasuredRecord", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> getLastMeasuredRecord(@RequestBody Map<String,String> request) {
        RequestVerifyUtil.printRequestBody("/getLastMeasuredRecord",request.toString());
        MonitoringRelationshipService monitoringRelationshipManager = new MonitoringRelationshipService();
        HashMap<String,String> result=monitoringRelationshipManager.getLastMeasuredRecord(request);
        return result;
    }

    @PostMapping(
      value = "/verifyCode", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> verifyCode(@RequestBody Map<String,String> request) {
        RequestVerifyUtil.printRequestBody("/verifyCode",request.toString());
        MonitoringRelationshipService monitoringRelationshipManager = new MonitoringRelationshipService();
        HashMap<String,String> result=monitoringRelationshipManager.verifyCode(request);
        return result;
    }

    // @PostMapping(
    //     value = "/addMonitoringRelationship", consumes = "application/json", produces = "text/plain")
    //   public String addMonitoringRelationship(@RequestBody MonitoringRelationship_1To1 monitoringRelationship) {
    //       RequestVerifyUtil.printRequestBody("/addMonitoringRelationship",monitoringRelationship.toString());
    //       MonitoringRelationshipService monitoringRelationshipManager = new MonitoringRelationshipService();
    //       String result=monitoringRelationshipManager.addMonitoringRelationship(monitoringRelationship);
    //       return result;
    //   }
      
    //   @PostMapping(
    //     value = "/getMonitoringRelationshipForHost", consumes = "text/plain", produces = "application/json")
    //   public String getMonitoringRelationshipForHost(@RequestBody String hostUserId) {
    //       RequestVerifyUtil.printRequestBody("/getMonitoringRelationshipForHost",hostUserId);
    //       MonitoringRelationshipService monitoringRelationshipManager = new MonitoringRelationshipService();
    //       String result=monitoringRelationshipManager.getMonitoringRelationshipForHost(hostUserId);
    //       return result;
    //   }

    //   @PostMapping(
    //     value = "/getMonitoringRelationshipForTarget", consumes = "text/plain", produces = "application/json")
    //   public String getMonitoringRelationshipForTarget(@RequestBody String targetUserId) {
    //       RequestVerifyUtil.printRequestBody("/getMonitoringRelationshipForTarget",targetUserId);
    //       MonitoringRelationshipService scheudleManager = new MonitoringRelationshipService();
    //       String result=scheudleManager.getMonitoringRelationshipForTarget(targetUserId);
    //       return result;
    //   }
      
    //   @DeleteMapping(
    //     value = "/deleteMonitoringRelationshipByHostOrTarget", consumes = "application/json", produces = "text/plain")
    //   public String deleteMonitoringRelationshipByHostOrTarget(@RequestBody MonitoringRelationship_1To1 relationship) {
    //       RequestVerifyUtil.printRequestBody("/deleteMonitoringRelationshipByHostOrTarge",relationship.toString());
    //       MonitoringRelationshipService scheudleManager = new MonitoringRelationshipService();
    //       String result=scheudleManager.deleteMonitoringRelationshipByHostOrTarget(relationship);
    //       return result;
    //   }
}
