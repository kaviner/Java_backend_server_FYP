package com.java_backend.apiserver.util;

import java.util.HashMap;

import org.json.JSONArray;

public class JsonUtil {

    public static HashMap<String, Integer> parseJsonToHashMap(JSONArray jsonArray) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String key = jsonArray.getJSONObject(i).getString("key");
            Integer value = jsonArray.getJSONObject(i).getInt("value");
            map.put(key, value);
        }
        return map;
    }
}
