package com.example.zaman_application_service.service;

import com.example.zaman_resource_service.entity.Appointment;
import com.example.zaman_resource_service.dto.CreateAppointmentDto;
import com.example.zaman_resource_service.dto.UpdateAppointmentDto;
import com.example.zaman_resource_service.entity.Participant;

import java.util.List;

public interface IAppointmentService {
    void create(CreateAppointmentDto createAppointmentDto);
    void update(UpdateAppointmentDto updateAppointmentDto);
    List<Appointment> getAll();
    Appointment getOne(long appointmentId);
    List<Appointment> getAllByUser(long userId);
    void delete(long appointmentId);
    void confirmAppointment(long appointmentId, long userId);
    void cancelAppointment(long appointmentId, long userId);
    List<Participant> getParticipantByUser(long userId);
}
