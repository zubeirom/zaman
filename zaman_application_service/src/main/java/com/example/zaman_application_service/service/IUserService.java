package com.example.zaman_application_service.service;
import com.example.zaman_resource_service.dto.Credentials;
import com.example.zaman_resource_service.entity.User;
import com.example.zaman_resource_service.dto.CreateUserDto;
import com.example.zaman_resource_service.dto.UpdateUserDto;
import com.example.zaman_resource_service.model.Token;

import java.util.List;

public interface IUserService {
    //void create(User createUserDto);
    void create(User user);
   // void update(UpdateUserDto updateUserDto);

    void update(User user);

    User getCurrentUser(long userId);

    void delete(long userId);

    void verifyUser(long userId);

    List<User> getAllUsers();

    Token loginUser(Credentials credentials);
}
