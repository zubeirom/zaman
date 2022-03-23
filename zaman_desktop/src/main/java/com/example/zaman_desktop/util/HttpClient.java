package com.example.zaman_desktop.util;

import com.example.zaman_desktop.model.User;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpClient {

    public static HttpResponse<JsonNode> get(String url) {
        try {
            return Unirest.get(url)
                    .header("Authorization", "Bearer " + Util.getSessionToken())
                    .asJson();
        } catch(UnirestException e) {
            return null;
        }
    }

    public static HttpResponse<JsonNode> post(String url, String json) {
        try {
            return Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + Util.getSessionToken())
                    .body(json)
                    .asJson();
        } catch(UnirestException e) {
            return null;
        }
    }

    public static HttpResponse<JsonNode> put(String url, Object data) {
        try {
            return Unirest.put(url)
                    .header("Authorization", "Bearer " + Util.getSessionToken())
                    .body(data)
                    .asJson();
        } catch(UnirestException e) {
            return null;
        }
    }

    public static HttpResponse<JsonNode> delete(String url) {
        try {
            return Unirest.delete(url)
                    .header("Authorization", "Bearer " + Util.getSessionToken())
                    .asJson();
        } catch(UnirestException e) {
            return null;
        }
    }

    public static HttpResponse<JsonNode> postWOAuth(String url, String json) {
        try {
            return Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .body(json)
                    .asJson();
        } catch (UnirestException e) {
            return null;
        }
    }
}
