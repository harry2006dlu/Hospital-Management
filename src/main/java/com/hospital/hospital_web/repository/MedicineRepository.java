package com.hospital.hospital_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.hospital_web.model.Medicine;

@Repository 
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    // Cung cấp các hàm CRUD cơ bản
}
