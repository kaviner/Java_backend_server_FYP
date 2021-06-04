package com.java_backend.apiserver.controller;

import com.java_backend.apiserver.model.MonitoringRelationship;
import com.java_backend.apiserver.util.RequestVerifyUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class MonitoringController {
    @PostMapping(
        value = "/addMonitoringRelationship", consumes = "application/json", produces = "text/plain")
      public String addSchedule(@RequestBody MonitoringRelationship monitoringRelationship) {
          RequestVerifyUtil.printRequestBody("/addMonitoringRelationship",monitoringRelationship.toString());
          MonitoringRelationship monitoringRelationshipManager = new MonitoringRelationship();
          String result=monitoringRelationshipManager.addMonitoringRelationship();
          return result;
      }
      
      @PostMapping(
        value = "/getMonitoringRelationshipForHost", consumes = "text/plain", produces = "application/json")
      public String getScheduleInfoForOwner(@RequestBody String hostUserId) {
          RequestVerifyUtil.printRequestBody("/getMonitoringRelationshipForHost",hostId);
          MonitoringRelationship monitoringRelationshipManager = new MonitoringRelationship();
          String result=monitoringRelationshipManager.getMonitoringRelationshipForHost(hostId);
          return result;
      }

      @PostMapping(
        value = "/getMonitoringRelationshipForTarget", consumes = "text/plain", produces = "application/json")
      public String getMonitoringRelationshipForTarget(@RequestBody String targetUserId) {
          RequestVerifyUtil.printRequestBody("/getMonitoringRelationshipForTarget",targetUserId);
          MonitoringRelationship scheudleManager = new MonitoringRelationship();
          String result=scheudleManager.getMonitoringRelationshipForTarget(targetUserId);
          return result;
      }
      
      @DeleteMapping(
        value = "/hostDeleteMonitoringRelationshipTarget", consumes = "text/plain", produces = "application/json")
      public String hostDeleteMonitoringRelationshipTarget(@RequestBody MonitoringRelationship relationship) {
          RequestVerifyUtil.printRequestBody("/hostDeleteMonitoringRelationshipTarget",relationship.toString());
          MonitoringRelationship scheudleManager = new MonitoringRelationship();
          String result=scheudleManager.hostDeleteMonitoringRelationshipTarget(relationship);
          return result;
      }
      
      @DeleteMapping(
        value = "/targetDeleteMonitoringRelationshipFromHost", consumes = "text/plain", produces = "application/json")
      public String targetDeleteMonitoringRelationshipFromHost(@RequestBody String hostUserId) {
          RequestVerifyUtil.printRequestBody("/targetDeleteMonitoringRelationshipFromHost",hostUserId);
          MonitoringRelationship scheudleManager = new MonitoringRelationship();
          String result=scheudleManager.targetDeleteMonitoringRelationshipFromHost(hostUserId);
          return result;
      }
}
