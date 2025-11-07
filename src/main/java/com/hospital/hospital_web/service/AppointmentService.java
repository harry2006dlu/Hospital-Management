package com.hospital.hospital_web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital_web.model.Appointment;
import com.hospital.hospital_web.repository.AppointmentRepository;

@Service 
public class AppointmentService {

    @Autowired 
    private AppointmentRepository appointmentRepository;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    public Appointment save(Appointment appointment) {
        // Có thể thêm logic kiểm tra Lịch khám và Thời gian khám hợp lệ
        return appointmentRepository.save(appointment);
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
