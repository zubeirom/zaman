package com.example.zaman_desktop.service;

import com.example.zaman_desktop.model.CreateAppointmentDto;
import com.example.zaman_desktop.model.Appointment;
import com.example.zaman_desktop.model.Participant;

import java.util.HashMap;
import java.util.List;

public interface IAppointmentService {
    List<Participant> getAppointments();

    Appointment getAppointment(String appointmentId);

    void createAppointment(CreateAppointmentDto createAppointmentDto);

    void acceptAppointment(String appointmentId) throws IllegalAccessException;

    void declineAppointment(String appointmentId) throws IllegalAccessException;

    List<Appointment> searchAppointment(HashMap<String, String> parameters);
}
