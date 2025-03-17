/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LoginDAO;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import model.GoogleAccount;
import model.GoogleLogin;
import model.Iconstant;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author PC
 */
@WebServlet(name = "LoginControl", urlPatterns = {"/login"})
public class LoginControl extends HttpServlet {

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
            out.println("<title>Servlet LoginControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginControl at " + request.getContextPath() + "</h1>");
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

        String code = request.getParameter("code");
        String error = request.getParameter("error");

        // Check code or error
        if (code == null && error == null) {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        // Nếu có code hoặc error, xử lý đăng nhập Google 
        LoginDAO dao = new LoginDAO();
        HttpSession session = request.getSession();

        if (error != null && !error.isEmpty()) {
            request.setAttribute("mssErr", "Google authentication was canceled");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        if (code != null && !code.isEmpty()) {
            try {
                String accessToken = GoogleLogin.getToken(code);
                GoogleAccount googleAccount = GoogleLogin.getUserInfo(accessToken);

                if (googleAccount != null && googleAccount.getEmail() != null) {

                    User existingUser = dao.getUserByEmail(googleAccount.getEmail());

                    if (existingUser != null) {
                        // if exsited, login
                        session.setAttribute("sessionLogin", existingUser);
                        session.setMaxInactiveInterval(60 * 60 * 24);
                        response.sendRedirect("home");
                        return;
                    } else {

                        String userName = googleAccount.getEmail().split("@")[0];
                        String password = "123";
                        String fullName = googleAccount.getName();
                        int roleID = 0;
                        String image = googleAccount.getPicture();
                        String email = googleAccount.getEmail();
                        Date birthday = new Date();
                        String address = "";
                        String phone = "";

                        dao.signUpNew(userName, fullName, password, roleID, image, email, birthday, address, phone);

                        User newUser = dao.getUserByEmail(email);

                        if (newUser != null) {
                            session.setAttribute("sessionLogin", newUser);
                            session.setMaxInactiveInterval(60 * 60 * 24);
                            response.sendRedirect("profile");
                        } else {
                            request.setAttribute("mssErr", "Failed to create account");
                            request.getRequestDispatcher("Login.jsp").forward(request, response);
                        }
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Google login error: " + e.getMessage());
                e.printStackTrace();
                request.setAttribute("mssErr", "Error during Google authentication: " + e.getMessage());
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            }
        }
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
        LoginDAO dao = new LoginDAO();
        HttpSession session = request.getSession();

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        User acc = dao.login(user, pass);

        if (acc != null) {
            session.setAttribute("sessionLogin", acc);
            session.setMaxInactiveInterval(60 * 60 * 24);
            response.sendRedirect("home");
        } else {
            request.setAttribute("mssErr", "User or pass not correct");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
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
