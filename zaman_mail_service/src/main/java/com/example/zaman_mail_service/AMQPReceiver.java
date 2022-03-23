package com.example.zaman_mail_service;

import com.example.zaman_resource_service.AMQPMessageTypes;
import com.example.zaman_resource_service.model.AMQPParticipantsMessage;
import com.example.zaman_resource_service.model.AMQPUserMessage;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Component
public class AMQPReceiver {

    public final static String RECEIVER_METHOD = "receiveMessage";

    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    IMailService mailService;

    Logger logger = LoggerFactory.getLogger(AMQPReceiver.class);

    public void receiveMessage(String message) {
        try {
            logger.info("NEW MESSAGE: " + message);
            forwardMessage(message);
            latch.countDown();
        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e.toString());
        }
    }

    public void forwardMessage(String message) {
        Map map = new Gson().fromJson(message, Map.class);
        String title = (String) map.get("title");
        if(title.equals(AMQPMessageTypes.VERIFY_MAIL)) {
            AMQPUserMessage userMessage = new Gson().fromJson(message, AMQPUserMessage.class);
            mailService.verifyEmail(userMessage.getUser());
        } else if(title.equals(AMQPMessageTypes.NEW_APPOINTMENT_MAIL)) {
            AMQPParticipantsMessage participantsMessage = new Gson().fromJson(message, AMQPParticipantsMessage.class);
            mailService.sendNewAppointmentMail(participantsMessage.getParticipants());
        } else if(title.equals(AMQPMessageTypes.APPOINTMENT_STARTED)) {
            AMQPParticipantsMessage participantsMessage = new Gson().fromJson(message, AMQPParticipantsMessage.class);
            mailService.sendAppointmentStartedMail(participantsMessage.getParticipants());
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
