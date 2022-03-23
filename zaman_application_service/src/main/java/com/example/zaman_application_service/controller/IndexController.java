package com.example.zaman_application_service.controller;

import com.example.zaman_application_service.service.IUserService;
import com.example.zaman_application_service.service.UserService;
import com.example.zaman_resource_service.dto.Credentials;
import com.example.zaman_resource_service.entity.User;
import com.example.zaman_resource_service.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {
    @Autowired
    IUserService userService;

    @PostMapping("/register")
    @ResponseBody
    public void register(@RequestBody User user) {
        userService.create(user);
    }

    @PostMapping("/login")
    @ResponseBody
    public Token login(@RequestBody Credentials credentials) {
        Token token = userService.loginUser(credentials);
        System.out.println(token.getToken());
        return token;
    }
}
