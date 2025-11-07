package com.hospital.hospital_web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.hospital_web.model.Appointment;
import com.hospital.hospital_web.service.AppointmentService;

@Controller
@RequestMapping("/appointments") 
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Các tùy chọn Tình trạng từ AppointmentsPage cũ
    private final String[] STATUS_OPTIONS = {"Xếp lịch", "Đã hoàn tất", "Hủy bỏ", "No Show"};

    // Hiển thị danh sách lịch hẹn
    // URL: /appointments
    @GetMapping
    public String listAppointments(Model model) {
        List<Appointment> appointments = appointmentService.findAll();
        model.addAttribute("appointments", appointments);
        model.addAttribute("content", "appointments/appointment_list"); 
        
        return "layout"; // Trả về templates/appointments/appointment_list.html
    }

    // Hiển thị Form Thêm Lịch hẹn Mới
    // URL: /appointments/new
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("appointment", new Appointment()); 
        model.addAttribute("statusOptions", STATUS_OPTIONS);
        model.addAttribute("action", "add");
        return "appointments/appointment_form"; // Trả về templates/appointments/appointment_form.html
    }
    
    // Xử lý Form Thêm/Chỉnh Sửa Lịch hẹn
    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.save(appointment);
        return "redirect:/appointments"; 
    }
    
    // Hiển thị Form Chỉnh Sửa Lịch hẹn
    // URL: /appointments/edit/1
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Appointment> appointment = appointmentService.findById(id);
        if (appointment.isPresent()) {
            model.addAttribute("appointment", appointment.get());
            model.addAttribute("statusOptions", STATUS_OPTIONS);
            model.addAttribute("action", "edit");
            return "appointments/appointment_form";
        }
        return "redirect:/appointments"; 
    }
    
    // Xóa Lịch hẹn
    // URL: /appointments/delete/1
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteById(id);
        return "redirect:/appointments";
    }
}
