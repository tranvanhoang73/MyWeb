
package com.mycompany.myweb.dao;


import com.mycompany.myweb.dataconnection.DBConnect;
import com.mycompany.myweb.dto.Student;
import java.sql.*;  
import java.util.ArrayList;  
import java.util.List;  

public class StudentDAO {  
    // Phương thức lấy tất cả sinh viên  
    public List<Student> getAllStudents() {  
        List<Student> studentList = new ArrayList<>();  
        String sql = "SELECT * FROM students.student";  

        try (Connection connect = DBConnect.getConnection();  
             PreparedStatement pre = connect.prepareStatement(sql);  
             ResultSet rs = pre.executeQuery()) {  

            while (rs.next()) {  
                int id = rs.getInt("id");  
                String studentId = rs.getString("student_id");  
                String name = rs.getString("name");  
                float gpa = rs.getFloat("gpa");   
                String imageName = rs.getString("image_name"); // Chỉ lấy tên hình ảnh  
                studentList.add(new Student(id, studentId, name, gpa, imageName));  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return studentList;  
    }  

    // Phương thức thêm sinh viên  
    public void addStudent(Student student) throws SQLException {  
           System.out.println("da vao addStudent roi.");  
        String sql = "INSERT INTO `students`.`student` (`student_id`, `name`, `gpa`, `image_name`) VALUES (?, ?, ?, ?);"; 
        
        try (Connection connect = DBConnect.getConnection();  
             PreparedStatement pre = connect.prepareStatement(sql)) {  
            pre.setString(1, student.getStudentId());  
            pre.setString(2, student.getName());  
            pre.setFloat(3, student.getGpa());  
            pre.setString(4, student.getImageName()); // Lưu tên hình ảnh  
            pre.executeUpdate();  
        }  
    }  

    // Phương thức cập nhật sinh viên  
    public void updateStudent(Student student) throws SQLException {  
        String sql = "UPDATE student SET name = ?, gpa = ?, image_name = ? WHERE student_id = ?";  
        try (Connection connect = DBConnect.getConnection();  
             PreparedStatement pre = connect.prepareStatement(sql)) {  
            pre.setString(1, student.getName());  
            pre.setFloat(2, student.getGpa());  
            pre.setString(3, student.getImageName()); // Cập nhật tên hình ảnh  
            pre.setString(4, student.getStudentId());  
            pre.executeUpdate();  
        }  
    }  

    // Phương thức xóa sinh viên  
    public void deleteStudent(String studentId) throws SQLException {  
        String sql = "DELETE FROM student WHERE student_id = ?";  
        try (Connection connect = DBConnect.getConnection();  
             PreparedStatement pre = connect.prepareStatement(sql)) {  
            pre.setString(1, studentId);  
            pre.executeUpdate();  
        }  
    }  
}  