package com.java_backend.apiserver.controller;

import java.util.HashMap;

import com.java_backend.apiserver.model.QueryModel;
import com.java_backend.apiserver.model.QueryModelForSchedule;
import com.java_backend.apiserver.model.Schedule;
import com.java_backend.apiserver.mongo_operation.ScheduleService;
import com.java_backend.apiserver.util.RequestVerifyUtil;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    
    @PostMapping(
        value = "/addSchedule", consumes = "application/json", produces = "application/json")
      public HashMap<String,String> addSchedule(@RequestBody Schedule schedule) {
          RequestVerifyUtil.printRequestBody("/addSchedule",schedule.toString());
          ScheduleService scheudleManager = new ScheduleService(schedule);
          HashMap<String,String> result=scheudleManager.addNewSchedule();
          return result;
      }
      
      @PostMapping(
        value = "/getScheduleInfoForOwner", consumes = "application/json", produces = "application/json")
      public String getScheduleInfoForOwner(@RequestBody QueryModelForSchedule qModule) {
          RequestVerifyUtil.printRequestBody("/getScheduleInfoForOwner",qModule.toString());
          ScheduleService scheudleManager = new ScheduleService();
          String result=scheudleManager.getScheduleInfoForOwner(qModule);
          return result;
      }

      @PostMapping(
        value = "/updateSchedule", consumes = "application/json", produces = "text/plain")
      public String updateSchedule(@RequestBody Schedule schedule) {
          RequestVerifyUtil.printRequestBody("/updateSchedule",schedule.toString());
          ScheduleService scheudleManager = new ScheduleService();
          String result=scheudleManager.updateSchedule(schedule);
          return result;
      }
      
      @DeleteMapping(
        value = "/deleteSchedule", consumes = "text/plain", produces = "text/plain")
      public String deleteSchedule(@RequestBody String scheduleId) {
          RequestVerifyUtil.printRequestBody("/updateSchedule",scheduleId);
          ScheduleService scheudleManager = new ScheduleService();
          String result=scheudleManager.deleteSchedule(scheduleId);
          return result;
      } 
}