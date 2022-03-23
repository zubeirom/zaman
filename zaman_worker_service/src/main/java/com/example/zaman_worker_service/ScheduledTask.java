package com.example.zaman_worker_service;

import com.example.zaman_resource_service.AMQPMessageTypes;
import com.example.zaman_resource_service.Constants;
import com.example.zaman_resource_service.entity.Appointment;
import com.example.zaman_resource_service.entity.Participant;
import com.example.zaman_resource_service.model.AMQPParticipantsMessage;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class ScheduledTask {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    public Timestamp getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return Timestamp.valueOf(now.truncatedTo(ChronoUnit.MINUTES));
    }

    @Scheduled(fixedRate = 60000)
    public void listCurrentParticipants() {
        List<Appointment> ap = appointmentRepository.getAptByCurrDateTime();
        if (!ap.isEmpty()) {
            sendNotification(ap);
        }
    }

    public void sendNotification(List<Appointment> appointments) {
        for (Appointment appointment : appointments) {
            List<Participant> participants = participantRepository.getParticipantByAppointment(appointment);
            logger.info("APPOINTMENT STARTING => SEND MESSAGE: " + appointment.getTitle());
            AMQPParticipantsMessage participantsMessage = new AMQPParticipantsMessage(AMQPMessageTypes.APPOINTMENT_STARTED, participants);
            rabbitTemplate.convertAndSend(Constants.AMQP_EXCHANGE, Constants.AMQP_MAIL_ROUTING_KEY, new Gson().toJson(participantsMessage));
        }
    }
}
