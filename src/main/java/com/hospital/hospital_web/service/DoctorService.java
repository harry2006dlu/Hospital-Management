package com.hospital.hospital_web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital_web.model.Doctor;
import com.hospital.hospital_web.repository.DoctorRepository;

import jakarta.annotation.PostConstruct;

@Service // Đánh dấu đây là một Spring Service
public class DoctorService {

    @Autowired // Tự động tiêm DoctorRepository vào lớp này
    private DoctorRepository doctorRepository;

    private Map<Long, Doctor> doctorCache = new HashMap<>();

    @PostConstruct
    public void initializeDoctorCache() {
        System.out.println("--- [DSA] Đang nạp dữ liệu Bác sĩ vào HashMap Cache ---");
        
        // Lấy tất cả bác sĩ từ CSDL
        List<Doctor> allDoctors = doctorRepository.findAll();

        // Đưa từng bác sĩ vào HashMap
        for (Doctor doctor : allDoctors) {
            doctorCache.put(doctor.getId(), doctor);
        }
        
        System.out.println("--- [DSA] Nạp Cache hoàn tất. Tổng số: " + doctorCache.size() + " bác sĩ ---");
    }

    // 3. Phương thức TÌM KIẾM MỚI dùng HashMap (tốc độ O(1))
    public Doctor findDoctorByIdUsingHashMap(Long id) {
        System.out.println("--- [DSA] Tìm kiếm Bác sĩ ID: " + id + " bằng HashMap.get() ---");
        
        // Đây chính là thao tác tìm kiếm O(1) của HashMap
        return doctorCache.get(id);
    }

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
