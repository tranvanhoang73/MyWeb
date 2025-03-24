
import com.mycompany.myweb.dao.StudentDAO;
import com.mycompany.myweb.dataconnection.DBConnect;
import com.mycompany.myweb.dto.Student;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] arg) {
            
     StudentDAO studentDAO = new StudentDAO();  
            List<Student> studentList = studentDAO.getAllStudents();  
            for (Student student : studentList) {  
                System.out.println(student);               
            }
    }
}
