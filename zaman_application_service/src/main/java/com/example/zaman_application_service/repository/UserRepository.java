package com.example.zaman_application_service.repository;

import com.example.zaman_resource_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmail(String email);
}

