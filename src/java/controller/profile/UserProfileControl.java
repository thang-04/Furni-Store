/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import dal.ProfileDao;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.apache.commons.fileupload2.jakarta.JakartaServletDiskFileUpload;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;

/**
 *
 * @author PC
 */
@WebServlet(name = "UserProfileControl", urlPatterns = {"/profile"})
public class UserProfileControl extends HttpServlet {

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
            out.println("<title>Servlet UserProfileControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProfileControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProfileDao dao = new ProfileDao();
        String uid = request.getParameter("uID");
        HttpSession session = request.getSession();
        session.setAttribute("sessionLogin", dao.getUserById(uid));
        session.setMaxInactiveInterval(60 * 60 * 60 * 24);
        request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProfileDao dao = new ProfileDao();
        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String birthDayStr = request.getParameter("birthDay");
        String uid = request.getParameter("uid");
        Date birthday = null;
        User acc = dao.getUserById(uid);
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthDayStr);
        } catch (ParseException ex) {
            Logger.getLogger(UserProfileControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        dao.updateProfile(username, fullName, acc.getPass(), acc.getRoleId(), acc.getImage(), email, birthday, address, phone, uid);

        HttpSession session = request.getSession();
        session.setAttribute("sessionLogin", dao.getUserById(uid));
        session.setMaxInactiveInterval(60 * 60 * 60 * 24);
         request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
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
