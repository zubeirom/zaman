package com.example.zaman_resource_service.dto;

import com.example.zaman_resource_service.entity.Appointment;
import com.example.zaman_resource_service.entity.User;

import java.util.List;

public class CreateAppointmentDto {
    Appointment appointment;
    List<User> users;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
