package com.example.zaman_mail_service;

import com.example.zaman_resource_service.entity.User;
import com.example.zaman_resource_service.entity.Participant;

import javax.mail.MessagingException;
import java.util.List;

public interface IMailService {
    void sendNewAppointmentMail(List<Participant> participant);
    void sendAppointmentStartedMail(List<Participant> participant);
    void sendAppointmentCancelledMail(Participant participant);
    void verifyEmail(User user);
}
