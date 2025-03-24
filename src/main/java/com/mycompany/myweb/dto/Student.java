
package com.mycompany.myweb.dto;
public class Student {  
    private int id;  
    private String studentId;  
    private String name;  
    private float gpa;  
    private String imageName;  // Chỉ lưu tên hình ảnh  

    public Student(int id, String studentId, String name, float gpa, String imageName) {  
        this.id = id;  
        this.studentId = studentId;  
        this.name = name;  
        this.gpa = gpa;  
        this.imageName = imageName;  
    }  

    // Getters and Setters  
    public int getId() { return id; }  
    public void setId(int id) { this.id = id; }  
  
    public String getStudentId() { return studentId; }  
    public void setStudentId(String studentId) { this.studentId = studentId; }  
  
    public String getName() { return name; }  
    public void setName(String name) { this.name = name; }  
  
    public float getGpa() { return gpa; }  
    public void setGpa(float gpa) { this.gpa = gpa; }  
  
    public String getImageName() { return imageName; }  // Thay đổi tên  
    public void setImageName(String imageName) { this.imageName = imageName; }  
}  