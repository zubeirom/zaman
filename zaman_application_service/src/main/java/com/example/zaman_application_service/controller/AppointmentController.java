
package com.example.zaman_application_service.controller;

import com.example.zaman_application_service.service.IAppointmentService;
import com.example.zaman_resource_service.dto.CancelAppointmentDto;
import com.example.zaman_resource_service.dto.CreateAppointmentDto;
import com.example.zaman_resource_service.dto.UpdateAppointmentDto;
import com.example.zaman_resource_service.entity.Appointment;
import com.example.zaman_resource_service.entity.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @PostMapping()
    @ResponseBody
    public void createAppointment(@RequestBody CreateAppointmentDto createAppointmentDto) {
        appointmentService.create(createAppointmentDto);
    }

    @GetMapping()
    @ResponseBody
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAll();
    }

    @PutMapping()
    @ResponseBody
    public void updateAppointment(@RequestBody UpdateAppointmentDto updateAppointmentDto) {
        appointmentService.update(updateAppointmentDto);
    }

    @RequestMapping(value="/appointments/user/{appointmentId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Appointment> getAllAppointmentsByUser(@PathVariable("userId") long userId) {
        return appointmentService.getAllByUser(userId);
    }

    @RequestMapping(value = "/appointments/{appointmentId}", method = RequestMethod.GET)
    @ResponseBody
    public Appointment getAppointment(@PathVariable("appointmentId") long appointmentId) {
        return appointmentService.getOne(appointmentId);
    }

    @DeleteMapping("/appointments/{appointmentId}")
    @ResponseBody
    public void deleteAppointment(@PathVariable("appointmentId") long appointmentId) {
        appointmentService.delete(appointmentId);
    }

    @PutMapping("/accept")
    @ResponseBody
    public void acceptAppointment(@RequestParam(name = "aid") String appointmentId, @RequestHeader("userId") String userId) {
        appointmentService.confirmAppointment(Long.parseLong(appointmentId), Long.parseLong(userId));
    }

    @PutMapping("/decline")
    @ResponseBody
    public void declineAppointment(@RequestParam(name = "aid") String appointmentId, @RequestHeader("userId") String userId) {
        appointmentService.cancelAppointment(Long.parseLong(appointmentId), Long.parseLong(userId));
    }

    @GetMapping("/participants")
    @ResponseBody
    public List<Participant> getAppointmentByParticipant(@RequestHeader("userId") String userId) {
        return appointmentService.getParticipantByUser(Long.parseLong(userId));
    }
}