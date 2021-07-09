package com.java_backend.apiserver.controller;

import java.util.HashMap;
import java.util.Map;

import com.java_backend.apiserver.model.MonitoringRelationship_1To1;
import com.java_backend.apiserver.mongo_operation.MonitoringRelationshipService;
import com.java_backend.apiserver.mongo_operation.StatisticService;
import com.java_backend.apiserver.util.RequestVerifyUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {
    @PostMapping(value = "/getBPMStatistic", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> getBPMStatistic(@RequestBody Map<String,String> userInfo) {
        RequestVerifyUtil.printRequestBody("/getBPMStatistic",userInfo.toString());
        StatisticService statisticService = new StatisticService();
        HashMap<String,String> result=statisticService.getBPMStatistic(userInfo.get("userID").toString());
        return result;
    }

    @PostMapping(value = "/getHighestAndLowestStressLevelActivity", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> getHighestAndLowestStressLevelActivity(@RequestBody Map<String,String> userInfo) {
        RequestVerifyUtil.printRequestBody("/getHighestAndLowestStressLevelActivity",userInfo.toString());
        StatisticService statisticService = new StatisticService();
        HashMap<String,String> result=statisticService.getHighestAndLowestStressLevelActivity(userInfo.get("userID").toString());
        return result;
    }

    @PostMapping(value = "/getRestingAvgHRAndPPI", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> getRestingAvgHRAndPPI(@RequestBody Map<String,String> userInfo) {
        RequestVerifyUtil.printRequestBody("/getRestingAvgHRAndPPI",userInfo.toString());
        StatisticService statisticService = new StatisticService();
        HashMap<String,String> result=statisticService.getRestingAvgHRAndPPI(userInfo.get("userID").toString());
        return result;
    }
  
}
