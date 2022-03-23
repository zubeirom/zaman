package com.example.zaman_desktop.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointment {
    private long id;
    private String location;
    private String title;
    private String description;
    private boolean cancelled = false;
    private String cancellationReason = null;
    private Timestamp startsAt;
    private Timestamp endsAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Appointment() {}

    public Appointment(long id, String location, String title, String description, boolean cancelled, String cancellationReason, Timestamp startsAt, Timestamp endsAt, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.location = location;
        this.title = title;
        this.description = description;
        this.cancelled = cancelled;
        this.cancellationReason = cancellationReason;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Appointment(String location, String title, String description, Timestamp startsAt, Timestamp endsAt) {
        this.location = location;
        this.title = title;
        this.description = description;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public Timestamp getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Timestamp startsAt) {
        this.startsAt = startsAt;
    }

    public Timestamp getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Timestamp endsAt) {
        this.endsAt = endsAt;
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
