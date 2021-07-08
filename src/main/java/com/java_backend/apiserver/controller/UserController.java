package com.java_backend.apiserver.controller;

import java.util.HashMap;
import java.util.Map;

import com.java_backend.apiserver.mongo_operation.UserService;
import com.java_backend.apiserver.util.RequestVerifyUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {
    @PostMapping(value = "/checkUserIDExist",consumes = "application/json", produces = "application/json")
    public HashMap<String,String> checkUserIDExist(@RequestBody Map<String,String> user) {
        RequestVerifyUtil.printRequestBody("/checkUserIDExist", user.toString());
        UserService userService = new UserService();
        HashMap<String,String> result = userService.checkUserIDExist(user.get("_id"));
        return result;
    }
}
