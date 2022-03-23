package com.example.zaman_application_service.service;

import com.example.zaman_resource_service.entity.Appointment;
import com.example.zaman_resource_service.entity.Participant;


import com.example.zaman_application_service.repository.ParticipantRepository;
import com.example.zaman_resource_service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService implements IParticipantService {

    @Autowired
    ParticipantRepository participantRepository;

    @Override
    public void create(Participant participant) {
        participantRepository.save(participant);
    }

    @Override
    public void update(Participant participant) {
        participantRepository.save(participant);
    }

    @Override
    public List<Participant> getAll() {
        return participantRepository.findAll();
    }

    @Override
    public Participant getOne(long participantId) {
        return participantRepository.findById(participantId).orElse(null);
    }

    @Override
    public List<Participant> saveMany(List<Participant> users) {
        return participantRepository.saveAll(users);
    }

    @Override
    public List<Participant> findByAppointmentAndUser(Appointment appointment, User user) {
        return participantRepository.findByAppointmentAndUser(appointment, user);
    }

    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> findByUser(User user) {
        return participantRepository.findParticipantByUser(user);
    }
}
