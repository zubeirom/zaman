package com.example.zaman_resource_service.entity;

import java.sql.Timestamp;
import javax.persistence.*;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String location;
    @NotNull
    private String title;
    private String description;
    @Column(columnDefinition = "boolean default false")
    private boolean cancelled;
    private String cancellationReason;
    @NotNull
    private Timestamp startsAt;
    @NotNull
    private Timestamp endsAt;
    @CreationTimestamp
    private Timestamp createdAt;
    @CreationTimestamp
    private Timestamp updatedAt;

    public Appointment() {
    }

    public Appointment(String title, String description, boolean cancelled, String cancellationReason,
            Timestamp startsAt, Timestamp endsAt, Timestamp updatedAt) {
        this.title = title;
        this.description = description;
        this.cancelled = cancelled;
        this.cancellationReason = cancellationReason;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.updatedAt = updatedAt;
    }

    public Appointment(String title, String description, String location, Timestamp startsAt, Timestamp endsAt) {
        this.title = title;
        this.description = description;
        this.location = location;
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
