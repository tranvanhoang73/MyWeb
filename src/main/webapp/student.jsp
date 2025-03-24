<%@page import="com.mycompany.myweb.dao.StudentDAO"%>  
<%@page import="com.mycompany.myweb.dto.Student"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ page import="java.util.List" %>  

<!DOCTYPE html>  
<html>  
<head>  
    <title>Quản Lý Sinh Viên</title>  
    <script>  
        function selectStudent(studentId, name, gpa, imageName) {  
            document.getElementById('studentId').value = studentId;  
            document.getElementById('name').value = name;  
            document.getElementById('gpa').value = gpa;  
            document.getElementById('studentImage').src = 'images/' + imageName; // Hiển thị ảnh lớn  
            document.getElementById('studentImage').style.display = 'block'; // Hiển thị ảnh  
            //document.getElementById('studentId').disabled = true; // Không cho phép chỉnh sửa mã sinh viên  
        }  
    </script>  
    <style>  
        #studentList {  
            max-height: 200px; /* Chỉnh sửa theo nhu cầu thiết kế của bạn */  
            overflow-y: auto;  
            border: 1px solid #ccc;  
            margin-top: 10px;  
            width: 100%; /* Chiếm chiều rộng 100% để phù hợp với form */  
        }  
        #imageContainer {  
            width: 30%; /* Chiếm chiều rộng 30% trang */  
            display: inline-block; /* Để nằm ngang với form nhập liệu */  
            vertical-align: top; /* Căn chỉnh phía trên */  
            text-align: center;  
        }  
        /* Định dạng bảng sinh viên */  
        table {  
            width: 70%; /* Chiếm chiều rộng 70% trang */  
            border-collapse: collapse;  
            display: inline-block; /* Để nằm ngang với khung ảnh */  
            vertical-align: top; /* Căn chỉnh phía trên */  
        }  
        th, td {  
            padding: 8px;  
            text-align: left;  
            border: 1px solid #ddd;  
        }  
        tr:hover {  
            background-color: #f5f5f5; /* Tô màu khi di chuột qua hàng */  
        }  
        img {  
            max-width: 100%;  
            max-height: 300px;  
        }  
    </style>  
</head>  
<body>  
    <h2>Quản Lý Sinh Viên</h2>  

    <form action="StudentServlet" method="post" enctype="multipart/form-data">  
        <input type="hidden" id="existingImage" name="existingImage" value=""> <!-- Lưu tên hình ảnh cũ -->  
        <input type="hidden" id="action" name="action" value="add">  
        

        <div style="display: flex; align-items: flex-start;"> <!-- Flex layout cho layout chính -->  
            <div style="flex: 1; padding-right: 20px;"> <!-- Phần tử flex cho form nhập liệu -->  
                <label for="studentId">Mã Sinh Viên:</label>  
                <input type="text" id="studentId" name="studentId" required><br><br>  

                <label for="name">Tên Sinh Viên:</label>  
                <input type="text" id="name" name="name" required><br><br>  

                <label for="gpa">Điểm GPA:</label>  
                <input type="text" id="gpa" name="gpa" required><br><br>  

                <label for="image">Ảnh Sinh Viên:</label>  
                <input type="file" id="image" name="image" accept="image/*"><br><br>  

                <input type="submit" value="Lưu">  
                <input type="reset" value="Hủy" onclick="document.getElementById('studentId').disabled = false;">  
            </div>   

            <div id="imageContainer">  
                <h3>Ảnh Sinh Viên</h3>  
                <img id="studentImage" src="" alt="Ảnh lớn sinh viên" style="display:none;"> <!-- Hiển thị ảnh lớn tại đây -->  
            </div>  
        </div>  
    </form>  

    <h3>Danh Sách Sinh Viên</h3>  
    <div id="studentList">  
        <table>  
            <tr>  
                <th>Mã Sinh Viên</th>  
                <th>Tên Sinh Viên</th>  
                <th>Điểm GPA</th>  
                <th>Ảnh</th>  
            </tr>  
            <%  
                StudentDAO studentDAO = new StudentDAO();  
                List<Student> studentList = studentDAO.getAllStudents();  
                for (Student student : studentList) {  
            %>  
            <tr onclick="selectStudent('<%= student.getStudentId() %>', '<%= student.getName() %>', '<%= student.getGpa() %>', '<%= student.getImageName() %>')" style="cursor:pointer;">  
                <td><%= student.getStudentId() %></td>  
                <td><%= student.getName() %></td>  
                <td><%= student.getGpa() %></td>  
                <td><img src="images/<%= student.getImageName() %>" alt="Ảnh sinh viên" style="width:50px;height:50px;"></td>                           
            </tr>  
            <%  
                }  
            %>  
        </table>  
    </div>  
</body>  
</html>  


