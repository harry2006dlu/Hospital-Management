package com.hospital.hospital_web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital_web.model.Medicine;
import com.hospital.hospital_web.repository.MedicineRepository;

@Service 
public class MedicineService {

    @Autowired 
    private MedicineRepository medicineRepository;

    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public Optional<Medicine> findById(Long id) {
        return medicineRepository.findById(id);
    }

    public Medicine save(Medicine medicine) {
        // Logic nghiệp vụ
        return medicineRepository.save(medicine);
    }

    public void deleteById(Long id) {
        medicineRepository.deleteById(id);
    }
}
