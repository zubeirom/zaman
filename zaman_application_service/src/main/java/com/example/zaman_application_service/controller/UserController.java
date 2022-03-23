package com.example.zaman_application_service.controller;


import com.example.zaman_resource_service.entity.User;
import com.example.zaman_application_service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping()
    @ResponseBody
    public void createUser(@RequestBody User user) {
        userService.create(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getCurrentUser(@PathVariable("userId") long userId) {
        System.out.println("Request received");
        return userService.getCurrentUser(userId);
    }

    @PutMapping()
    @ResponseBody
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @DeleteMapping("/{userId}")
    @ResponseBody
    public void deleteUser(@PathVariable("userId") long userId) {
        userService.delete(userId);
    }

    @GetMapping()
    @ResponseBody
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @PostMapping("verify")
    public void verifyUser(@RequestHeader("userId") String userId) {
        userService.verifyUser(Long.parseLong(userId));
        throw new ResponseStatusException(HttpStatus.OK, "OK");
    }
}
