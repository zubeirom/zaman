package com.example.zaman_resource_service.entity;

import java.sql.Timestamp;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;
    private Timestamp lastUsed;
    @CreationTimestamp
    private Timestamp createdAt;

    public Location(long id, String title, User user, Timestamp lastUsed, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.lastUsed = lastUsed;
        this.createdAt = createdAt;
    }

    public Location(String title, User user, Timestamp lastUsed) {
        this.title = title;
        this.user = user;
        this.lastUsed = lastUsed;
    }

    public Location() {}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
