package com.example.zaman_desktop.service;

import com.example.zaman_desktop.controller.NewAppointmentController;
import com.example.zaman_desktop.model.CreateAppointmentDto;
import com.example.zaman_desktop.model.Appointment;
import com.example.zaman_desktop.model.Participant;
import com.example.zaman_desktop.util.HttpClient;
import com.example.zaman_desktop.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AppointmentService implements IAppointmentService {
    private final String appointmentUrl = Util.API_URL + "/appointments";
    private final String acceptAppointmentUrl = Util.API_URL + "/appointments/accept/";
    private final String declineAppointmentUrl = Util.API_URL + "/appointments/decline/";

    @Override
    public List<Participant> getAppointments() {
        HttpResponse<JsonNode> data = HttpClient.get(appointmentUrl + "/participants");
        System.out.println(data.getBody().toString());
        if (data == null) {
            return null;
        }
        return Arrays.asList(new Gson().fromJson(data.getBody().toString(), Participant[].class));
    }

    @Override
    public Appointment getAppointment(String appointmentId) {
        HttpResponse<JsonNode> data = HttpClient.get(appointmentUrl + "/" + appointmentId);
        if (data == null) {
            return null;
        }
        return new Gson().fromJson(data.getBody().toString(), Appointment.class);
    }

    @Override
    public void createAppointment(CreateAppointmentDto createAppointmentDto) {
        String json = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create().toJson(createAppointmentDto);
        HttpClient.post(appointmentUrl, json);
    }

    @Override
    public void acceptAppointment(String appointmentId) throws IllegalAccessException {
        HttpResponse<JsonNode> data = HttpClient.get(acceptAppointmentUrl + appointmentId);
        if (data == null) {
            throw new IllegalAccessException("Appointment has been not accepted");
        }
    }

    @Override
    public void declineAppointment(String appointmentId) throws IllegalAccessException {
        HttpResponse<JsonNode> data = HttpClient.get(declineAppointmentUrl + appointmentId);
        if (data == null) {
            throw new IllegalAccessException("Appointment has been rejected");
        }
    }

    @Override
    public List<Appointment> searchAppointment(HashMap<String, String> parameters) {
        return null;
    }
    
}
