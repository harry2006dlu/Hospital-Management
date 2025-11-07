package com.hospital.hospital_web.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointments") // Tên bảng trong MySQL là 'appointments'
@Data 
@NoArgsConstructor 
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "Tên bệnh nhân", nullable = false)
    private String tenBenhNhan;

    @Column(name = "Tên bác sĩ", nullable = false)
    private String tenBacSi;

    @Column(name = "appointment_date") // Tên cột không có dấu trong CSDL
    private LocalDate appointmentDate; 
    
    @Column(name = "appointment_time") // Tên cột không có dấu trong CSDL
    private LocalTime appointmentTime; 

    @Column(name = "Nguyên nhân", columnDefinition = "TEXT") 
    private String nguyenNhan;
    
    @Column(name = "Tình trạng", nullable = false)
    private String tinhTrang; 
}
