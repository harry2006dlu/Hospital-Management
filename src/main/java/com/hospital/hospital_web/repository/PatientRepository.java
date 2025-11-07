package com.hospital.hospital_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_web.model.Patient;

@Repository 
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Các phương thức CRUD được cung cấp bởi JpaRepository
}
