# 🏥 Hệ thống Quản lý Bệnh viện (Hospital Management System)

Đây là dự án xây dựng hệ thống quản lý bệnh viện bằng Java (Spring Boot) và CSDL SQL. Hệ thống được thiết kế để quản lý các nghiệp vụ cơ bản trong bệnh viện như quản lý bệnh nhân, bác sĩ, lịch hẹn, v.v.

## ✨ Tính năng chính

*(Bạn hãy liệt kê các tính năng chính xác của dự án. Dưới đây là ví dụ:)*

* **Quản lý Bệnh nhân:** Thêm, sửa, xóa, tìm kiếm thông tin bệnh nhân.
* **Quản lý Bác sĩ:** Quản lý hồ sơ, chuyên khoa, và lịch làm việc của bác sĩ.
* **Quản lý Lịch hẹn:** Cho phép bệnh nhân (hoặc lễ tân) đặt lịch, hủy lịch, và xem lịch hẹn.
* **Quản lý Khoa:** Quản lý thông tin các khoa (VD: Khoa Nội, Khoa Ngoại...).
* **Hệ thống Tài khoản:** Đăng nhập, đăng ký, và phân quyền cho các vai trò (Admin, Bác sĩ, Bệnh nhân).
* **[Tính năng khác...]**

## 💻 Công nghệ sử dụng

* **Backend:** Java (Spring Boot)
* **Frontend:** HTML5, CSS3, JavaScript
* **Database:** SQL (VD: MySQL, PostgreSQL, SQL Server)
* **Build Tool:** Maven
* **(Có thể có):** Thymeleaf (hoặc JSP) để render phía server.
* **(Có thể có):** Spring Data JPA, Spring Security, v.v.

## 🚀 Cài đặt & Khởi chạy

Dưới đây là các bước để chạy dự án này trên máy cục bộ của bạn.

### 1. Yêu cầu
* Java JDK 11 (hoặc 8, 17... tùy thuộc vào dự án của bạn)
* Maven 3+
* Một CSDL SQL (VD: MySQL Workbench, PostgreSQL)

### 2. Cài đặt

1.  **Clone repository:**
    ```bash
    git clone [https://github.com/harry2006dlu/Hospital-Management.git](https://github.com/harry2006dlu/Hospital-Management.git)
    cd Hospital-Management
    ```

2.  **Cài đặt Database:**
    * Tạo một database mới trong CSDL của bạn (ví dụ: `hospital_db`).
    * *(Nếu bạn có file .sql để tạo bảng, hãy hướng dẫn ở đây. VD: "Import file `database_schema.sql` vào CSDL của bạn.")*

3.  **Cấu hình kết nối Database:**
    * Mở file `src/main/resources/application.properties` (hoặc `.yml`).
    * Cập nhật các thông tin sau cho phù hợp với CSDL của bạn:
      ```properties
      # Ví dụ cho MySQL
      spring.datasource.url=jdbc:mysql://localhost:3306/hospital_db
      spring.datasource.username=root
      spring.datasource.password=password_cua_ban

      # Cấu hình JPA (nếu dùng)
      spring.jpa.hibernate.ddl-auto=update
      ```

### 3. Khởi chạy

Sử dụng Maven wrapper (khuyến nghị) để build và chạy ứng dụng:

```bash
# Trên macOS/Linux
./mvnw clean spring-boot:run

# Trên Windows
./mvnw.cmd clean spring-boot:run
