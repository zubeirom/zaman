package com.example.zaman_mail_service;

import com.example.zaman_resource_service.entity.User;
import com.example.zaman_resource_service.entity.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailService implements IMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ITemplateService templateService;

    @Value("${spring.mail.username}")
    private String fromMail;

    Logger logger = LoggerFactory.getLogger(MailService.class);

    @Override
    public void sendNewAppointmentMail(List<Participant> participants) {
        String subject = "New appointment has been created";
        for (Participant participant : participants) {
            String html = templateService.convertAppointmentInformationHTML(participant.getAppointment(), participant.getUser());
            sendMimeMessage(html, participant.getUser().getEmail(), subject);
            logger.info("NEW APPOINTMENT MAIL SENT TO: " + participant.getUser().getEmail());
        }
    }

    @Override
    public void sendAppointmentStartedMail(List<Participant> participants) {
        String subject = "Reminder: your next appointment";
        for (Participant participant: participants) {
            String html = templateService.convertAppointmentStartedHTML(participant.getAppointment());
            sendMimeMessage(html, participant.getUser().getEmail(), subject);
            logger.info("APPOINTMENT STARTED MAIL SENT TO: " + participant.getUser().getEmail());
        }
    }

    @Override
    public void sendAppointmentCancelledMail(Participant participant) {
//        String subject = "Your appointment below has been cancelled";
//        String html = templateService.convertAppointmentInformationHTML(participant.getAppointment(), part);
//        sendMimeMessage(html, participant.getUser().getEmail(), subject);
    }

    @Override
    public void verifyEmail(User user) {
        String subject = "Verify Your Email!";
        sendMimeMessage(templateService.convertEmailVerificationHTML(user), user.getEmail(), subject);
        logger.info("EMAIL VERIFICATION MAIL SENT TO: " + user.getEmail());
    }

    private void sendMimeMessage(String html, String to, String subject) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(html, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(fromMail);
            javaMailSender.send(mimeMessage);
        } catch(Exception e) {
            throw new AmqpRejectAndDontRequeueException(e.toString());
        }
    }
}
