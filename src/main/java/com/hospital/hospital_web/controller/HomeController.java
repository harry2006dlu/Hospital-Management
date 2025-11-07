package com.hospital.hospital_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Xử lý yêu cầu truy cập trang chủ (Dashboard)
     * URL: GET / hoặc /dashboard
     */
    @GetMapping({"/", "/dashboard"})
    public String showDashboard(Model model) {
        model.addAttribute("content", "dashboard");
        return "layout";
    }
}