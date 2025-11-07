package com.hospital.hospital_web.repository;

import com.hospital.hospital_web.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Đánh dấu đây là một Spring Repository Component
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Spring Data JPA tự động cung cấp các phương thức:
    // findAll(), findById(Long id), save(Doctor doctor), deleteById(Long id)
    
    // Bạn có thể thêm các phương thức tìm kiếm phức tạp hơn tại đây, ví dụ:
    // List<Doctor> findByChuyenKhoa(String chuyenKhoa);
}
