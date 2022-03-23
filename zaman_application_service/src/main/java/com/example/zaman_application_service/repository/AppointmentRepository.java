package com.example.zaman_application_service.repository;

import com.example.zaman_resource_service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {}
