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
@Table(name = "medicines") 
@Data 
@NoArgsConstructor 
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "Họ và tên") // Tên Thuốc
    private String name;

    @Column(name = "Nhà sản xuất") 
    private String nhaSanXuat;

    @Column(name = "Ngày hết hạn") 
    private LocalDate ngayHetHan; 
    
    @Column(name = "Liều dùng") 
    private String lieuDung; 

    @Column(name = "Giá") // Giả định cột này lưu giá hoặc số lượng tồn kho (Stock)
    private Integer gia; 
    
    @Column(name = "Tình Trạng") 
    private String tinhTrang; 
}
