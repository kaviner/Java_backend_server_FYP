package com.java_backend.apiserver.controller;

import java.util.HashMap;
import java.util.Map;

import com.java_backend.apiserver.model.Activity;
import com.java_backend.apiserver.mongo_operation.ActivityService;
import com.java_backend.apiserver.util.RequestVerifyUtil;

import org.json.JSONArray;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ActivityController {
    @GetMapping(value = "/getAllActivityCategory", produces = "application/json")
    public String getAllActivityCategory() {
        RequestVerifyUtil.printRequestBody("/getAllActivity", "No request body");
        ActivityService measuredRecordService = new ActivityService();
        String result = measuredRecordService.getAllActivityCategory();
        return result;
    }

    @PostMapping(value = "/addNewActivityIfNotExist",consumes = "application/json", produces = "application/json")
    public HashMap<String,String> addNewActivityIfNotExist(@RequestBody Activity activity) {
        RequestVerifyUtil.printRequestBody("/addNewActivityIfNotExist", activity.toString());
        ActivityService measuredRecordService = new ActivityService();
        HashMap<String,String> result = measuredRecordService.addNewActivityIfNotExist(activity);
        return result;
    }
}
