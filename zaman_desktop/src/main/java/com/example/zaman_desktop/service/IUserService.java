package com.example.zaman_desktop.service;

import com.example.zaman_desktop.model.User;

import java.util.List;

public interface IUserService {
    void createUser(User user);
    boolean loginUser(String username, String password);
    User getCurrentUserRemote();
    List<User> getAllUsers();
}
