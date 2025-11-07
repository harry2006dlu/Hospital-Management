package com.hospital.hospital_web.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient") 
@Data 
@NoArgsConstructor 
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "Họ và tên", nullable = false)
    private String hoVaTen;

    @Column(name = "Ngày sinh", nullable = false)
    private LocalDate ngaySinh; 

    @Column(name = "Giới tính", nullable = false)
    private String gioiTinh;

    @Column(name = "Số điện thoại", nullable = false)
    private String soDienThoai;
    
    private String email; 
    
    @Column(name = "Địa chỉ", columnDefinition = "TEXT") 
    private String diaChi;

    @Column(name = "Chẩn đoán", columnDefinition = "TEXT") 
    private String chanDoan;
    
    @Column(name = "Tình trạng điều trị")
    private String tinhTrangDieuTri;

    @Column(name = "Tình trạng")
    private String tinhTrang; 
}
