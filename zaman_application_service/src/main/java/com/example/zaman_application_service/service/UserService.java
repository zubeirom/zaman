package com.example.zaman_application_service.service;

import com.example.zaman_application_service.repository.UserRepository;
import com.example.zaman_application_service.util.JWTUtil;
import com.example.zaman_resource_service.AMQPMessageTypes;
import com.example.zaman_resource_service.Constants;
import com.example.zaman_resource_service.dto.Credentials;
import com.example.zaman_resource_service.entity.User;
import com.example.zaman_resource_service.model.AMQPUserMessage;
import com.example.zaman_resource_service.model.Token;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    public void create(User user) {
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            AMQPUserMessage userMessage = new AMQPUserMessage(AMQPMessageTypes.VERIFY_MAIL, savedUser);
            rabbitTemplate.convertAndSend(Constants.AMQP_EXCHANGE, Constants.AMQP_MAIL_ROUTING_KEY, new Gson().toJson(userMessage));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exists already");
        }
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public User getCurrentUser(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void delete(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void verifyUser(long userId) {
        Optional<User> op = userRepository.findById(userId);
        if(op.isPresent()) {
            User user = op.get();
            user.setVerified(true);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Token loginUser(Credentials credentials) {
        System.out.println(credentials.toString());
        List<User> users = userRepository.findByEmail(credentials.getEmail());
        if(users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User does not exists");
        }
        User user = users.get(0);
        System.out.println(user);
        if(!new BCryptPasswordEncoder().matches(credentials.getPassword(), user.getPassword())) {
            System.out.println("Wrong password");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User does not exists");
        }
        return new Token(new JWTUtil().generateToken(user));
    }

    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }
}
