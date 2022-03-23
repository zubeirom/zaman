package com.example.zaman_desktop.service;

import com.example.zaman_desktop.model.Credentials;
import com.example.zaman_desktop.model.User;
import com.example.zaman_desktop.util.Alerts;
import com.example.zaman_desktop.util.HttpClient;
import com.example.zaman_desktop.util.Util;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class UserService implements IUserService {
    private final String userUrl = Util.API_URL + "/users";

    public void createUser(User createUserData) {
        String json = new Gson().toJson(createUserData);
        HttpClient.postWOAuth(Util.API_URL + "/register", json);
    }

    @Override
    public boolean loginUser(String username, String password) {
        String json = new Gson().toJson(new Credentials(username, password));
        HttpResponse<JsonNode> data = HttpClient.postWOAuth(Util.API_URL + "/login", json);
        if(data != null && data.getStatus() < 300) {
            Map m = new Gson().fromJson(data.getBody().toString(), Map.class);
            Util.setToken(m.get("token").toString());
            return true;
        }
        Alerts.error("Error", "Something went wrong", "Email or password is wrong, or account is not verified, please check inbox").showAndWait();
        return false;
    }

    public User getCurrentUserRemote() {
        HttpResponse<JsonNode> data = HttpClient.get(userUrl);
        if(data == null) {
            return null;
        }
        return new Gson().fromJson(data.getBody().toString(), User.class);
    }

    public List<User> getAllUsers() {
        HttpResponse<JsonNode> data = HttpClient.get(userUrl);
        if(data == null) {
            return null;
        }

        return Arrays.asList(new Gson().fromJson(data.getBody().toString(), User[].class));
    }
}
