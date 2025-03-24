
package com.mycompany.myweb.dataconnection;


import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  

public class DBConnect {  
    private static final String URL = "jdbc:mysql://localhost/students"; 
    private static final String USER = "root"; // Thay đổi tên người dùng  
    private static final String PASSWORD = "123456789"; // Thay đổi mật khẩu  

    public static Connection getConnection() throws SQLException {  
        Connection connection = null;  
        
        try {  
            // Tải driver JDBC  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            // Tạo kết nối  
            connection = DriverManager.getConnection(URL, USER, PASSWORD); 
            
            System.out.println("connect done.");
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
            throw new SQLException("Could not find database driver.");  
        }  

        return connection;  
    }  
}  
