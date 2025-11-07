package com.hospital.hospital_web.controller;
import java.util.Arrays;
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

import com.hospital.hospital_web.model.Doctor;
import com.hospital.hospital_web.service.DoctorService;

@Controller
@RequestMapping("/doctor") // Tất cả các URL liên quan đến bác sĩ sẽ bắt đầu bằng /doctor
public class DoctorController {
//doctors
    @Autowired
    private DoctorService doctorService;

    // Danh sách chuyên khoa được dùng chung cho cả list và form
    private List<String> getSpecialties() {
        return Arrays.asList("Tim mạch", "Nội tiết", "Da liễu", "Nhi khoa", "Phẫu thuật", "Nhãn Khoa", "Hồi Sức", "Khác");
    }

    // Hiển thị danh sách bác sĩ (Thay thế màn hình DoctorsPage chính)
    // URL: /doctor
    @GetMapping
    public String listDoctors(Model model) {
        List<Doctor> doctors = doctorService.findAll();
        // Gửi danh sách bác sĩ và các tùy chọn ComboBox/Radio Button tới View
        model.addAttribute("doctors", doctors);
        model.addAttribute("specialties", getSpecialties());
        //chèn vào layout
        model.addAttribute("content", "doctor/doctor_list");
        return "layout"; // Trả về file HTML: src/main/resources/templates/doctor/doctor_list.html
    }

    // Hiển thị Form Thêm Bác sĩ Mới (Thay thế việc mở DynamicForm ở chế độ Create)
    // URL: /doctor/new
    @GetMapping("/new")
    public String showAddForm(Model model) {
        // Gửi một đối tượng Doctor rỗng để Thymeleaf bind dữ liệu vào
        model.addAttribute("doctor", new Doctor()); 
        model.addAttribute("specialties", getSpecialties());
        model.addAttribute("action", "add");
        model.addAttribute("content", "doctor/doctor_form");
        return "layout"; // Trả về file HTML: src/main/resources/templates/doctor/doctor_form.html
    }
    
    // Xử lý Form Thêm Bác sĩ
    // URL: /doctor
    @PostMapping
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctorService.save(doctor);
        return "redirect:/doctor"; // Quay lại trang danh sách sau khi thêm
    }
    
    // Hiển thị Form Chỉnh Sửa Bác sĩ (Thay thế việc mở DynamicForm ở chế độ Update)
    // URL: /doctor/edit/1
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Doctor> doctor = doctorService.findById(id);
        if (doctor.isPresent()) {
            model.addAttribute("doctor", doctor.get());
            model.addAttribute("specialties", getSpecialties());
            model.addAttribute("action", "edit");
            model.addAttribute("content", "doctor/doctor_form");    
            return "layout";
        }
        return "redirect:/doctor"; // Nếu không tìm thấy, quay lại danh sách
    }
    
    // Xử lý Form Chỉnh Sửa Bác sĩ
    // URL: /doctor/edit/1
    @PostMapping("/edit/{id}")
    public String updateDoctor(@ModelAttribute Doctor doctor) {
        doctorService.save(doctor); // ID đã có sẵn trong đối tượng doctor
        return "redirect:/doctor";
    }

    // Xóa Bác sĩ
    // URL: /doctor/delete/1
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorService.deleteById(id);
        return "redirect:/doctor";
    }
}
