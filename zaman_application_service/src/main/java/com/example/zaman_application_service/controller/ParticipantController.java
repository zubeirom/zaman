package com.example.zaman_application_service.controller;

import com.example.zaman_application_service.service.IParticipantService;
import com.example.zaman_resource_service.entity.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParticipantController {

    @Autowired
    IParticipantService participantService;

    @PostMapping("/participants")
    @ResponseBody
    public void createParticipant(@RequestBody Participant participant) {
        participantService.create(participant);
    }

    @PutMapping("/participants")
    @ResponseBody
    public void updateParticipant(@RequestBody Participant participant) {
        participantService.update(participant);
    }

    @RequestMapping(value = "/participants/{participantId}", method = RequestMethod.GET)
    @ResponseBody
    public Participant getCurrentParticipant(@PathVariable("participantId") long participantId) {
        return participantService.getOne(participantId);
    }

    @GetMapping("/participants")
    @ResponseBody
    public List<Participant> getAllParticipants() {
        return participantService.getAll();
    }
}
