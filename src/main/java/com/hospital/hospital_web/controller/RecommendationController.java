package com.hospital.hospital_web.controller;

import com.hospital.hospital_web.model.Doctor;
import com.hospital.hospital_web.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Dùng @RestController để trả về JSON
@RequestMapping("/api/recommend")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    /**
     * API này sẽ được trang HTML gọi
     * URL sẽ là: /api/recommend?patientName=Nguyễn Văn A
     */
    @GetMapping
    public List<Doctor> getRecommendations(@RequestParam String patientName) {
        // Gọi thuật toán Graph của bạn
        return recommendationService.recommendDoctors(patientName);
    }
}