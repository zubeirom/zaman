package com.example.zaman_desktop.model;

import java.sql.Timestamp;

public class Participant {
    private long id;
    private User user;
    private Appointment appointment;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Participant(long id, User user, Appointment appointment, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.user = user;
        this.appointment = appointment;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Participant(User user, Appointment appointment, String status, Timestamp updatedAt) {
        this.user = user;
        this.appointment = appointment;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public Participant() {}

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getStatus() { return status;}

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
