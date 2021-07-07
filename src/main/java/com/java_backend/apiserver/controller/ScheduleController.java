package com.java_backend.apiserver.controller;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.java_backend.apiserver.model.QueryModel;
import com.java_backend.apiserver.model.QueryModelForSchedule;
import com.java_backend.apiserver.model.Schedule;
import com.java_backend.apiserver.mongo_operation.ScheduleService;
import com.java_backend.apiserver.util.RequestVerifyUtil;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

  @PostMapping(value = "/addSchedule", consumes = "application/json", produces = "application/json")
  public HashMap<String, String> addSchedule(@RequestBody Schedule schedule) {
    RequestVerifyUtil.printRequestBody("/addSchedule", schedule.toString());
    ScheduleService scheudleManager = new ScheduleService(schedule);
    HashMap<String, String> result = scheudleManager.addNewSchedule();
    return result;
  }

  @PostMapping(value = "/getScheduleInfoForOwner", consumes = "application/json", produces = "application/json")
  public String getScheduleInfoForOwner(@RequestBody QueryModelForSchedule qModule) {
    RequestVerifyUtil.printRequestBody("/getScheduleInfoForOwner", qModule.toString());
    ScheduleService scheudleManager = new ScheduleService();
    String result = scheudleManager.getScheduleInfoForOwner(qModule);
    return result;
  }

  @PostMapping(value = "/updateSchedule", consumes = "application/json", produces = "application/json")
  public HashMap<String,String> updateSchedule(@RequestBody Schedule schedule) {
    RequestVerifyUtil.printRequestBody("/updateSchedule", schedule.toString());
    ScheduleService scheudleManager = new ScheduleService();
    HashMap<String,String> result = scheudleManager.updateSchedule(schedule);
    return result;
  }

  @PostMapping(value = "/deleteSchedule", consumes = "application/json", produces = "application/json")
  public HashMap<String, String> deleteSchedule(@RequestBody Map<String, String> schedueledId) {
    RequestVerifyUtil.printRequestBody("/deleteSchedule", schedueledId.toString());
    ScheduleService scheudleManager = new ScheduleService();
    HashMap<String, String> result = scheudleManager.deleteSchedule(schedueledId);
    return result;
  }
}