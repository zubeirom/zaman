package com.example.zaman_resource_service.model;

import com.example.zaman_resource_service.entity.Participant;

import java.util.List;

public class AMQPParticipantsMessage {
    private String title;
    private List<Participant> participants;

    public AMQPParticipantsMessage(String title, List<Participant> participants) {
        this.title = title;
        this.participants = participants;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
