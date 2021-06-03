package com.java_backend.apiserver.controller;

import com.java_backend.apiserver.model.Schedule;
import com.mongodb.client.MongoClients;

import org.json.JSONObject;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    
    @PostMapping(
        value = "/addSchedule", consumes = "application/json", produces = "application/json")
      public String createPerson(@RequestBody Schedule schedule) {
          System.out.println(schedule.toString());
          return "HI";
      }
}