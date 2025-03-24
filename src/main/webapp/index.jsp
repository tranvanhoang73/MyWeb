<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>  
<html>  
<head>  
    <title>Calculate Sum</title>  
</head>  
<body>  
    <h2>Calculator</h2>  
    <form action="CalculateServlet" method="post">  
        <label for="numberA">Số A:</label>  
        <input type="text" id="numberA" name="numberA" value="<%= request.getAttribute("numberA") != null ? request.getAttribute("numberA") : "" %>" required><br><br>  
        
        <label for="numberB">Số B:</label>  
        <input type="text" id="numberB" name="numberB" value="<%= request.getAttribute("numberB") != null ? request.getAttribute("numberB") : "" %>" required><br><br>  
        
        <label for="result">Tổng:</label>  
        <input type="text" id="result" name="result" value="<%= request.getAttribute("result") != null ? request.getAttribute("result") : "" %>" readonly><br><br>  
        
        <input type="submit" value="Calculate">  
    </form>  
        
        <br> <!-- Ngắt dòng để tách các phần -->  
    <a href="student.jsp">Chuyển đến trang Quản Lý Sinh Viên</a> <!-- Thêm liên kết -->  
</body>  
</html>  
