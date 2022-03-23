package com.example.zaman_desktop.model;

public class AppointmentTable {
    private String title = null;
    private String status = null;
    private String fromNow = null;
    private String begins = null;
    private String ends = null;
    private String location;
    private String participants;
    private String note;

    public AppointmentTable() {}

    public AppointmentTable(String title, String status, String fromNow, String begins, String ends, String location, String participants, String note) {
        this.title = title;
        this.status = status;
        this.fromNow = fromNow;
        this.begins = begins;
        this.ends = ends;
        this.location = location;
        this.participants = participants;
        this.note = note;
    }

    public String getFromNow() {
        return fromNow;
    }

    public void setFromNow(String fromNow) {
        this.fromNow = fromNow;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBegins() {
        return begins;
    }

    public void setBegins(String begins) {
        this.begins = begins;
    }


    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
