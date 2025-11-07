package com.hospital.hospital_web.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; // Cần cho Bắt đầu và Kết thúc (TimeField)
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor") // Ánh xạ tới bảng 'doctors'
@Data 
@NoArgsConstructor 
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    // Ánh xạ tới các cột tiếng Việt trong CSDL
    @Column(name = "Họ và tên")
    private String hoVaTen;

    @Column(name = "Chuyên khoa")
    private String chuyenKhoa;

    @Column(name = "Số điện thoại")
    private String soDienThoai;
    
    private String email; 
    
    @Column(name = "Giới tính")
    private String gioiTinh;

    @Column(name = "Kinh nghiệm") 
    private Integer kinhNghiem; 

    @Column(name = "Tình trạng")
    private String tinhTrang;

    @Column(name = "Bắt đầu")
    private LocalTime batDau; // Kiểu Time trong CSDL, dùng LocalTime

    @Column(name = "Kết thúc")
    private LocalTime ketThuc; // Kiểu Time trong CSDL, dùng LocalTime
}
