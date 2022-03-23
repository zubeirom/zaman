package com.example.zaman_mail_service;

import com.example.zaman_resource_service.entity.User;
import com.example.zaman_resource_service.entity.Appointment;

public interface ITemplateService {
    String convertAppointmentStartedHTML(Appointment appointment);
    String convertEmailVerificationHTML(User user);
    String convertAppointmentInformationHTML(Appointment appointment, User user);
}
