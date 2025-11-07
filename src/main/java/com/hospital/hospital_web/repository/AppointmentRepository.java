package com.hospital.hospital_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_web.model.Appointment;

@Repository 
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Nếu cần: List<Appointment> findByTenBenhNhanContaining(String tenBenhNhan);
}
