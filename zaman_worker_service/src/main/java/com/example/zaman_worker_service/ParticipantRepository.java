package com.example.zaman_worker_service;

import com.example.zaman_resource_service.entity.Appointment;
import com.example.zaman_resource_service.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.parser.Part;

import java.sql.Timestamp;
import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> getParticipantByAppointment(Appointment appointment);
}
