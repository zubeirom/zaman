package com.example.zaman_desktop.model;


import java.time.LocalDateTime;
import java.util.List;

public class CreateAppointmentDto {
    Appointment appointment;
    List<User> users;

    public CreateAppointmentDto(Appointment appointment, List<User> users) {
        this.appointment = appointment;
        this.users = users;
    }

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
