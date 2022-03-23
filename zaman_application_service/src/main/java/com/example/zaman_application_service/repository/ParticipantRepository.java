package com.example.zaman_application_service.repository;

import com.example.zaman_resource_service.entity.Appointment;
import com.example.zaman_resource_service.entity.Participant;
import com.example.zaman_resource_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByAppointmentAndUser(Appointment appointment, User user);
    List<Participant> findParticipantByUser(User user);
}
