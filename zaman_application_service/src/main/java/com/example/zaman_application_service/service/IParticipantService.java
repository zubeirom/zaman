package com.example.zaman_application_service.service;

import com.example.zaman_resource_service.entity.Appointment;
import com.example.zaman_resource_service.entity.Participant;
import com.example.zaman_resource_service.entity.User;
//import com.example.zaman_resource_service.dto.CreateParticipantDto;
//import com.example.zaman_resource_service.dto.UpdateParticipantDto;

import java.util.List;

public interface IParticipantService {
    void create(Participant participant);

    void update(Participant participant);

    List<Participant> getAll();

    Participant getOne(long participantId);

    List<Participant> saveMany(List<Participant> participants);

    List<Participant> findByAppointmentAndUser(Appointment appointment, User user);
}
