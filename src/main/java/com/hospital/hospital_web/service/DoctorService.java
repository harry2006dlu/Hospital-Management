package com.hospital.hospital_web.service;

import com.hospital.hospital_web.model.Doctor;
import com.hospital.hospital_web.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Đánh dấu đây là một Spring Service
public class DoctorService {

    @Autowired // Tự động tiêm DoctorRepository vào lớp này
    private DoctorRepository doctorRepository;

    // Lấy tất cả bác sĩ
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    // Lấy bác sĩ theo ID
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    // Lưu/Cập nhật bác sĩ
    public Doctor save(Doctor doctor) {
        // Đây là nơi chứa logic nghiệp vụ trước khi lưu (ví dụ: validation)
        return doctorRepository.save(doctor);
    }

    // Xóa bác sĩ
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }
}
