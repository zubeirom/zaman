package com.example.zaman_worker_service;

import com.example.zaman_resource_service.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> getAppointmentsByStartsAt(Timestamp startsAt);
    @Query(value = "select * from appointments where starts_at >= NOW() - interval 1 minute and starts_at <= NOW() + interval 1 minute;", nativeQuery = true)
    List<Appointment> getAptByCurrDateTime();
}
