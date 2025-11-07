package com.hospital.hospital_web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.hospital_web.model.Patient;
import com.hospital.hospital_web.repository.PatientRepository;

@Service 
public class PatientService {

    @Autowired 
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findAllPatientsAndSort() {
        // Lấy tất cả dữ liệu gốc
        List<Patient> patients = patientRepository.findAll();
        
        // GỌI THUẬT TOÁN ĐÃ TỰ TRIỂN KHAI
        return customInsertionSort(patients);
    }

    private List<Patient> customInsertionSort(List<Patient> list) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            // Lấy phần tử hiện tại để chèn vào vị trí đúng trong mảng con đã sắp xếp
            Patient current = list.get(i);
            int j = i - 1;

            // Di chuyển các phần tử lớn hơn 'current' sang phải
            // So sánh String bằng compareTo()
            while (j >= 0 && list.get(j).getHoVaTen().compareTo(current.getHoVaTen()) > 0) {
                // Đẩy phần tử sang phải
                list.set(j + 1, list.get(j));
                j--;
            }
            // Chèn phần tử hiện tại vào vị trí đúng
            list.set(j + 1, current);
        }
        return list;
    }
}
