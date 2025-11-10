package com.hospital.hospital_web.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital_web.model.Appointment;
import com.hospital.hospital_web.model.Doctor;
import com.hospital.hospital_web.repository.AppointmentRepository;
import com.hospital.hospital_web.repository.DoctorRepository;

import jakarta.annotation.PostConstruct;

@Service // Phải có @Service
public class RecommendationService {

    @Autowired
    private AppointmentRepository appointmentRepo;
    
    @Autowired
    private DoctorRepository doctorRepository; // Dùng để lấy thông tin bác sĩ

    // ----- LÕI DSA: GRAPH DÙNG STRING (HASHMAP LỒNG HASHSET) -----
    
    // Graph 1: Tên Bệnh nhân -> Set Tên Bác sĩ
    private Map<String, Set<String>> patientToDoctorGraph = new HashMap<>();
    
    // Graph 2: Tên Bác sĩ -> Set Tên Bệnh nhân
    private Map<String, Set<String>> doctorToPatientGraph = new HashMap<>();

    /**
     * Tải dữ liệu từ CSDL và XÂY DỰNG GRAPH (dùng String)
     * Đây là hàm thầy sẽ hỏi -> "Em dùng @PostConstruct để xây dựng Graph
     * khi ứng dụng khởi động"
     */
    @PostConstruct
    public void buildGraphFromStrings() {
        System.out.println("--- [DSA] Bắt đầu Xây dựng Graph (dùng String) ---");
        
        List<Appointment> allAppointments = appointmentRepo.findAll();
        
        for (Appointment app : allAppointments) {
            // Lấy tên (String) từ Appointment
            // Giả sử tên cột trong Appointment.java là: tenBenhNhan, tenBacSi
            String patientName = app.getTenBenhNhan(); 
            String doctorName = app.getTenBacSi();

            // RẤT QUAN TRỌNG: Chuẩn hóa dữ liệu (Xử lý "bẩn")
            // Đây là phần "ăn điểm" -> "Em chuẩn hóa data để 'A' và 'a' là một"
            if (patientName != null && doctorName != null) {
                patientName = patientName.trim().toLowerCase();
                doctorName = doctorName.trim().toLowerCase();

                // Thêm vào Graph 1 (Thao tác O(1))
                patientToDoctorGraph.putIfAbsent(patientName, new HashSet<>());
                patientToDoctorGraph.get(patientName).add(doctorName);

                // Thêm vào Graph 2 (Thao tác O(1))
                doctorToPatientGraph.putIfAbsent(doctorName, new HashSet<>());
                doctorToPatientGraph.get(doctorName).add(patientName);
            }
        }
        System.out.println("--- [DSA] Xây dựng Graph (String) hoàn tất ---");
    }

    /**
     * Thuật toán Gợi ý (dùng String)
     * Đây là hàm thầy sẽ hỏi -> "Em duyệt Graph 3 bậc (P -> D -> P -> D)"
     */
    public List<Doctor> recommendDoctors(String patientName) {
        
        // 1. Chuẩn hóa tên bệnh nhân đầu vào
        String normalizedPatientName = patientName.trim().toLowerCase();

        // 2. Lấy các bác sĩ PatientA đã khám (Duyệt Graph bậc 1)
        // Thao tác O(1) nhờ HashMap
        Set<String> doctorsSeenByPatientA = patientToDoctorGraph.getOrDefault(normalizedPatientName, Collections.emptySet());

        // 3. Tìm "Bệnh nhân tương đồng" (Duyệt Graph bậc 2)
        Set<String> similarPatients = new HashSet<>();
        for (String doctorName : doctorsSeenByPatientA) {
            // Thao tác O(1) nhờ HashMap
            similarPatients.addAll(doctorToPatientGraph.getOrDefault(doctorName, Collections.emptySet()));
        }
        similarPatients.remove(normalizedPatientName); // Loại bỏ chính mình (O(1))

        // 4. Đếm & Chấm điểm các bác sĩ MỚI (Duyệt Graph bậc 3)
        Map<String, Integer> recommendedDoctorScores = new HashMap<>();
        for (String similarPatient : similarPatients) {
            Set<String> doctorsSeenBySimilar = patientToDoctorGraph.getOrDefault(similarPatient, Collections.emptySet());
            
            for (String recommendedDoctorName : doctorsSeenBySimilar) {
                // Chỉ gợi ý bác sĩ mà PatientA CHƯA TỪNG KHÁM (O(1))
                if (!doctorsSeenByPatientA.contains(recommendedDoctorName)) {
                    recommendedDoctorScores.put(recommendedDoctorName, 
                        recommendedDoctorScores.getOrDefault(recommendedDoctorName, 0) + 1);
                }
            }
        }

        // 5. Sắp xếp và trả về ĐỐI TƯỢNG Doctor (thay vì chỉ String)
        List<String> sortedDoctorNames = recommendedDoctorScores.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();

        // Dùng CSDL (hoặc cache HashMap O(1) của bạn) để lấy Doctor
        Map<String, Doctor> allDoctorsMap = new HashMap<>();
        for (Doctor d : doctorRepository.findAll()) {
            // Chuẩn hóa tên bác sĩ trong CSDL
            allDoctorsMap.put(d.getHoVaTen().trim().toLowerCase(), d);
        }

        // Trả về danh sách Doctor hoàn chỉnh
        return sortedDoctorNames.stream()
                .map(name -> allDoctorsMap.get(name))
                .filter(Objects::nonNull) // Lọc bỏ nếu tên không khớp
                .collect(Collectors.toList());
    }
}