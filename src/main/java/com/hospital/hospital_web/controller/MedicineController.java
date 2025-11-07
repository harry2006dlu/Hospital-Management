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

import com.hospital.hospital_web.model.Medicine;
import com.hospital.hospital_web.service.MedicineService;

@Controller
@RequestMapping("/medicines") 
public class MedicineController {

    @Autowired
    private MedicineService medicineService;
    
    private final String[] STATUS_OPTIONS = {"Còn", "Hết hàng", "Sắp hết hạn"};

    // Hiển thị danh sách thuốc
    @GetMapping
    public String listMedicines(Model model) {
        List<Medicine> medicines = medicineService.findAll();
        model.addAttribute("medicines", medicines);
        model.addAttribute("content", "medicines/medicine_list"); 
        
        return "layout";
    }

    // Hiển thị Form Thêm Thuốc Mới
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("medicine", new Medicine()); 
        model.addAttribute("statusOptions", STATUS_OPTIONS);
        model.addAttribute("action", "add");
        return "medicines/medicine_form";
    }
    
    // Xử lý Form Thêm/Chỉnh Sửa Thuốc
    @PostMapping("/save")
    public String saveMedicine(@ModelAttribute Medicine medicine) {
        medicineService.save(medicine);
        return "redirect:/medicines"; 
    }
    
    // Hiển thị Form Chỉnh Sửa Thuốc
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Medicine> medicine = medicineService.findById(id);
        if (medicine.isPresent()) {
            model.addAttribute("medicine", medicine.get());
            model.addAttribute("statusOptions", STATUS_OPTIONS);
            model.addAttribute("action", "edit");
            return "medicines/medicine_form";
        }
        return "redirect:/medicines"; 
    }
    
    // Xóa Thuốc
    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {
        medicineService.deleteById(id);
        return "redirect:/medicines";
    }
}
