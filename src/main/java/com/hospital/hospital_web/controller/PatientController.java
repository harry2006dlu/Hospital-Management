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

import com.hospital.hospital_web.model.Patient;
import com.hospital.hospital_web.service.PatientService;

@Controller
@RequestMapping("/patients") // URL cơ sở cho tất cả các hành động liên quan đến bệnh nhân
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    // Các tùy chọn ComboBox/RadioButton (Lấy từ PatientsPage cũ)
    private final String[] GENDER_OPTIONS = {"Nam", "Nữ", "Khác"};
    private final String[] TREATMENT_STATUS_OPTIONS = {"Đang diễn ra", "Hoàn tất", "Chờ xử lý", "Dừng điều trị"};
    private final String[] STATUS_OPTIONS = {"Kích hoạt", "Ngừng"};


    /**
     * Hiển thị danh sách tất cả bệnh nhân
     * URL: GET /patients
     */
    @GetMapping
    public String listPatients(Model model) {
        List<Patient> patients = patientService.findAllPatientsAndSort();
        model.addAttribute("patients", patients);
        model.addAttribute("content", "patients/patient_list");
        return "layout"; // Trả về templates/patients/patient_list.html
    }

    /**
     * Hiển thị Form Thêm Bệnh nhân Mới
     * URL: GET /patients/new
     */
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient()); 
        model.addAttribute("action", "add");
        model.addAttribute("genderOptions", GENDER_OPTIONS);
        model.addAttribute("treatmentStatusOptions", TREATMENT_STATUS_OPTIONS);
        model.addAttribute("statusOptions", STATUS_OPTIONS);
        model.addAttribute("content", "patients/patient_form");
        return "layout";
    }
    
    /**
     * Xử lý Form Thêm/Chỉnh Sửa Bệnh nhân
     * URL: POST /patients/save
     */
    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient) {
        patientService.save(patient);
        return "redirect:/patients"; // Chuyển hướng về trang danh sách
    }
    
    /**
     * Hiển thị Form Chỉnh Sửa Bệnh nhân
     * URL: GET /patients/edit/{id}
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Patient> patient = patientService.findById(id);
        
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
            model.addAttribute("action", "edit");
            model.addAttribute("genderOptions", GENDER_OPTIONS);
            model.addAttribute("treatmentStatusOptions", TREATMENT_STATUS_OPTIONS);
            model.addAttribute("statusOptions", STATUS_OPTIONS);
            return "patients/patient_form";
        }
        
        return "redirect:/patients"; 
    }

    /**
     * Xử lý xóa Bệnh nhân
     * URL: GET /patients/delete/{id}
     */
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
        return "redirect:/patients";
    }
}