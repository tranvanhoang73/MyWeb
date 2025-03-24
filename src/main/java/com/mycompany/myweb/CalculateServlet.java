package com.mycompany.myweb;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CalculateServlet", urlPatterns = {"/CalculateServlet"})
public class CalculateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalculateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CalculateServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        //processRequest(request, response);
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String a = request.getParameter("numberA");  
        String b = request.getParameter("numberB");    
        
        // Ghi lại số A và B vào request attribute  
        request.setAttribute("numberA", a);  
        request.setAttribute("numberB", b);         
        // Chuyển đổi chuỗi thành số và tính tổng  
        try {  
            int numberA = Integer.parseInt(a);  
            int numberB = Integer.parseInt(b);  
            int sum = numberA + numberB;  

            // Ghi kết quả vào request attribute  
            request.setAttribute("result", sum);  
        } catch (NumberFormatException e) {  
            request.setAttribute("result", "Error: Invalid Input");  
        }  

        // Chuyển hướng về trang JSP  
        request.getRequestDispatcher("index.jsp").forward(request, response);        
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
