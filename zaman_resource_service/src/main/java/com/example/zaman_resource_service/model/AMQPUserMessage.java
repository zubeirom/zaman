package com.example.zaman_resource_service.model;

import com.example.zaman_resource_service.entity.User;

public class AMQPUserMessage {
    private String title;
    private User user;

    public AMQPUserMessage(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
