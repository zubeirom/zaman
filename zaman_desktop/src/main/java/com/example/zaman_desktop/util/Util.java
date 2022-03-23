package com.example.zaman_desktop.util;

import com.example.zaman_desktop.IndexApplication;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Util {
    public final static String API_URL = "http://localhost:8080/api";

    public static String getSessionToken() {
        try {
            InputStream inputStream = IndexApplication.class.getResourceAsStream("storage.json");
            InputStreamReader reader = new InputStreamReader(inputStream);
            Map map = new Gson().fromJson(reader, Map.class);
            return map.get("token").toString();
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void setToken(String token) {
        try {
            File f = new File(IndexApplication.class.getResource("storage.json").toURI());
            Map map = new HashMap();
            map.put("token", token);
            String c = new Gson().toJson(map);
            FileWriter fw = new FileWriter(f);
            fw.write(c);
            fw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
