package com.example.zaman_application_service.service;

import com.example.zaman_application_service.repository.AppointmentRepository;

import com.example.zaman_resource_service.AMQPMessageTypes;
import com.example.zaman_resource_service.Constants;
import com.example.zaman_resource_service.dto.CreateAppointmentDto;
import com.example.zaman_resource_service.dto.UpdateAppointmentDto;
import com.example.zaman_resource_service.entity.Appointment;

import com.example.zaman_resource_service.entity.Participant;
import com.example.zaman_resource_service.entity.Status;
import com.example.zaman_resource_service.entity.User;
import com.example.zaman_resource_service.model.AMQPParticipantsMessage;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Service
public class AppointmentService  implements IAppointmentService  {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    ParticipantService participantService;

    @Autowired
    UserService userService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void create(CreateAppointmentDto createAppointmentDto) {
        try {
            Appointment appointment  = appointmentRepository.save(createAppointmentDto.getAppointment());
            List<Participant> participants = new ArrayList<>();
            for (User user: createAppointmentDto.getUsers()) {
                Participant participant = new Participant(user, appointment, Status.PENDING);
                participants.add(participant);
            }
            List<Participant> savedParticipants = participantService.saveMany(participants);
            AMQPParticipantsMessage participantsMessage = new AMQPParticipantsMessage(AMQPMessageTypes.NEW_APPOINTMENT_MAIL, savedParticipants);
            rabbitTemplate.convertAndSend(Constants.AMQP_EXCHANGE, Constants.AMQP_MAIL_ROUTING_KEY, new Gson().toJson(participantsMessage));
        } catch(DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void update(UpdateAppointmentDto updateAppointmentDto) {
        Appointment appointment = new Appointment();
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAll() {
        return null;
    }

    @Override
    public Appointment getOne(long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    @Override
    public List<Appointment> getAllByUser(long userId) {
        return appointmentRepository.findAllById(Collections.singleton(userId));
    }

    @Override
    public void delete(long appointmentId) {

    }

    @Override
    public void confirmAppointment(long appointmentId, long userId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        Optional<User> user = userService.findById(userId);
        if(appointment.isEmpty() || user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Participant> participants = participantService.findByAppointmentAndUser(appointment.get(), user.get());
        if(participants.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Participant p = participants.get(0);
        p.setStatus(Status.ACCEPTED);
        participantService.save(p);
    }

    @Override
    public void cancelAppointment(long appointmentId, long userId) {
        Optional<Appointment> appointment = appointmentRepository.findById(appointmentId);
        Optional<User> user = userService.findById(userId);
        if(appointment.isEmpty() || user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Participant> participants = participantService.findByAppointmentAndUser(appointment.get(), user.get());
        if(participants.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Participant p = participants.get(0);
        p.setStatus(Status.DECLINE);
        participantService.save(p);
    }

    public List<Participant> getParticipantByUser(long userId) {
        Optional<User> ou = userService.findById(userId);
        if(ou.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return participantService.findByUser(ou.get());
    }
}
