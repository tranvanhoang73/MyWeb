
package com.mycompany.myweb.controller;

import com.mycompany.myweb.dao.StudentDAO;
import com.mycompany.myweb.dto.Student;
import jakarta.servlet.ServletException;  
import jakarta.servlet.annotation.MultipartConfig;  
import jakarta.servlet.annotation.WebServlet;  
import jakarta.servlet.http.HttpServlet;  
import jakarta.servlet.http.HttpServletRequest;  
import jakarta.servlet.http.HttpServletResponse;  
import jakarta.servlet.http.Part;  

import java.io.File;  
import java.io.IOException;  
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;  
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/StudentServlet")  
@MultipartConfig  

public class StudentServlet extends HttpServlet {  

   // private static final String IMAGES_DIR = "images";  // Đường dẫn hình ảnh  
 private static final String IMAGES_DIR = "images";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        String action = request.getParameter("action"); // Lấy hành động từ form  
        StudentDAO studentDAO = new StudentDAO();  

        // Thêm sinh viên  
        if ("add".equals(action)) {  
             
            String studentId = request.getParameter("studentId");  
            String name = request.getParameter("name");  
            float gpa = Float.parseFloat(request.getParameter("gpa"));  
            Part filePart = request.getPart("image");  
            String imageName = filePart.getSubmittedFileName();
             
            saveImageFile(filePart,imageName); // Lưu tên tệp hình ảnh  
         
            Student newStudent = new Student(0, studentId, name, gpa, imageName);  
            try {  
                studentDAO.addStudent(newStudent);  
            } catch (SQLException ex) {  
                Logger.getLogger(StudentServlet.class.getName()).log(Level.SEVERE, null, ex);  
            }  
        }  

        // Cập nhật sinh viên  
        if ("update".equals(action)) {  
          
        }  

        // Xóa sinh viên  
        if ("delete".equals(action)) {  
           String studentId = request.getParameter("studentId");  
           String imageName = request.getParameter("imageName"); // Lấy tên hình ảnh từ yêu cầu  
          try {  
            studentDAO.deleteStudent(studentId); // Xóa sinh viên khỏi cơ sở dữ liệu  
            // Xóa ảnh trong thư mục  
            deleteImageFile(imageName); // Gọi phương thức để xóa ảnh  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  

        response.sendRedirect("student.jsp"); // Chuyển hướng về trang JSP sau khi thực hiện xong  
    }  
    
    private void deleteImageFile(String imageName) {  
        File file = new File(getServletContext().getRealPath("/") + IMAGES_DIR + File.separator + imageName);  
        if (file.exists()) {  
            if (file.delete()) {  
                System.out.println("File đã được xóa: " + file.getAbsolutePath());  
            } else {  
                System.out.println("Không thể xóa file: " + file.getAbsolutePath());  
            }  
        }  
    }  

    private void saveImageFile(Part filePart, String imageName )
    {  
        // Lấy đường dẫn tới thư mục images  
        String uploadsPath = getServletContext().getRealPath("/") + IMAGES_DIR + File.separator;    
        File uploads = new File(uploadsPath); 
       // Nếu thư mục không tồn tại, có thể bạn muốn tạo nó (dù bạn đã tạo sẵn)  
       if (!uploads.exists()) {  
           boolean created = uploads.mkdirs();  
           if (created) {  
               System.out.println("Thư mục đã được tạo thành công: " + uploads.getAbsolutePath());  
           } else {  
               System.out.println("Không thể tạo thư mục: " + uploads.getAbsolutePath());  
           }  
       } else {  
           System.out.println("Thư mục đã tồn tại: " + uploads.getAbsolutePath());  
       }  
       // Kiểm tra và lưu file  
       File file = new File(uploads, imageName);  
       // Dùng try-with-resources để bắt đầu stream  
       try (InputStream input = filePart.getInputStream()) {  
           Files.copy(input, file.toPath());  
           System.out.println("File đã được upload thành công: " + file.getAbsolutePath());        
           // Set the filename in request attribute to use in JSP  
       } catch (IOException e) {  
           e.printStackTrace(); // In thông báo lỗi ra console  
          // response.getWriter().println("<h3>Có lỗi xảy ra khi upload file: " + e.getMessage() + "</h3>");  
       }  


    }  
}  