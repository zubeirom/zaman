package com.example.zaman_mail_service;

import com.example.zaman_resource_service.entity.User;
import com.example.zaman_resource_service.entity.Appointment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class TemplateService implements ITemplateService {
    private final String APPOINTMENTHTML = "templates/appointment-information.html";
    private final String VERIFICATIONTHTML = "templates/email-verification-template.html";
    private final String NOTIFICATIONHTML = "templates/started-appointment-notification.html";

    @Override
    public String convertAppointmentInformationHTML(Appointment appointment, User user) {
        String appointmentInfo = null;
        try {
            appointmentInfo = convertHTMLToString(APPOINTMENTHTML);
            appointmentInfo = appointmentInfo.replace("{{{appointmentTitle}}}", appointment.getTitle());
            appointmentInfo = appointmentInfo.replace("{{{appointmentDescription}}}", appointment.getDescription() == null ? "No Description" : appointment.getDescription());
            appointmentInfo = appointmentInfo.replace("{{{appointmentStartsAt}}}", appointment.getStartsAt().toLocalDateTime().toString());
            appointmentInfo = appointmentInfo.replace("{{{appointmentEndsAt}}}", appointment.getEndsAt().toLocalDateTime().toString());
            appointmentInfo = appointmentInfo.replace("{{{appointmentLocation}}}", appointment.getLocation() == null ? "Location not set" : appointment.getDescription());

            String token = new JWTUtil().generateToken(user);
            appointmentInfo = appointmentInfo.replace("{{{acceptAppointmentLink}}}", "http://localhost:8085/accept.html?aid=" + appointment.getId() + "&t=" + token);
            appointmentInfo = appointmentInfo.replace("{{{declineAppointmentLink}}}","http://localhost:8085/decline.html?aid=" + appointment.getId() + "&t=" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return appointmentInfo;
    }

    @Override
    public String convertEmailVerificationHTML(User user) {
        String verificationEmail = null;
        try {
            verificationEmail = convertHTMLToString(VERIFICATIONTHTML);
            String token = new JWTUtil().generateToken(user);
            System.out.println(token);
            verificationEmail = verificationEmail.replace("{{{emailVerificationLink}}}", "http://localhost:8085/verify.html?t=" + token);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return verificationEmail;
    }

    @Override
    public String convertAppointmentStartedHTML(Appointment appointment) {
        String startedNotification = null;
        try {
            startedNotification = convertHTMLToString(NOTIFICATIONHTML);
        } catch (IOException e) {
            e.printStackTrace();
        }

        startedNotification = startedNotification.replace("{{{appointmentTitle}}}", appointment.getTitle());
        startedNotification = startedNotification.replace("{{{appointmentDescription}}}", appointment.getDescription() == null ? "No Description" : appointment.getDescription());
        startedNotification = startedNotification.replace("{{{appointmentStartsAt}}}", appointment.getStartsAt().toLocalDateTime().toString());
        startedNotification = startedNotification.replace("{{{appointmentEndsAt}}}", appointment.getEndsAt().toLocalDateTime().toString());
        startedNotification = startedNotification.replace("{{{appointmentLocation}}}", appointment.getLocation() == null ? "Location not set" : appointment.getDescription());


        return startedNotification;
    }

    private String convertHTMLToString(String fileName) throws IOException {
        return new String(getFileAsIOStream(fileName).readAllBytes(), StandardCharsets.UTF_8);
    }

    private InputStream getFileAsIOStream(final String fileName) {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }

}
