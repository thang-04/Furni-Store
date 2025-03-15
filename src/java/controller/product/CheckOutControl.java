/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.product;

import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import model.Cart;
import model.Email;
import model.Item;
import model.Product;
import model.User;

/**
 *
 * @author PC
 */
@WebServlet(name = "CheckOutControl", urlPatterns = {"/checkout"})
public class CheckOutControl extends HttpServlet {

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
            out.println("<title>Servlet CheckOutControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutControl at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User sessionLogin = (User) session.getAttribute("sessionLogin");
        ProductDAO dao = new ProductDAO();
        List<Product> listP = dao.getAllProduct();
        Cookie[] cookie = request.getCookies();
        String txt = "";

        if (cookie != null) {
            for (Cookie o : cookie) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, listP);
        List<Item> listItem = cart.getItems();
        int n;
        if (listItem != null) {
            n = listItem.size();
        } else {
            n = 0;
        }

        request.setAttribute("dataUser", sessionLogin);
        request.setAttribute("size", n);
        request.setAttribute("cart", cart);
        if (sessionLogin != null) {
            request.getRequestDispatcher("CheckOut.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User sessionLogin = (User) session.getAttribute("sessionLogin");
        ProductDAO dao = new ProductDAO();
        List<Product> listP = dao.getAllProduct();
        Cookie[] cookie = request.getCookies();
        String txt = "";

        if (cookie != null) {
            for (Cookie o : cookie) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                }
            }
        }
        Cart cart = new Cart(txt, listP);
        List<Item> listItem = cart.getItems();
        int n;
        if (listItem != null) {
            n = listItem.size();
        } else {
            n = 0;
        }
   
        request.setAttribute("dataUser", sessionLogin);
        request.setAttribute("size", n);
        request.setAttribute("cart", cart);

        if (sessionLogin != null) {
            OrderDAO odao = new OrderDAO();
            odao.addOrder(sessionLogin, cart);
            Cookie c = new Cookie("cart", "");
            c.setMaxAge(0);
            response.addCookie(c);

            try {
                LocalDateTime currentDateTime = LocalDateTime.now();
                Email handleEmail = new Email();
                String sub = handleEmail.subjectOrder(sessionLogin.getFullName());
                String msg = handleEmail.messageOrder(currentDateTime, cart.getTotalMoney(), sessionLogin.getAddress());
                handleEmail.sendEmail(sub, msg, sessionLogin.getEmail());
            } catch (Exception e) {
                e.printStackTrace(); // In lỗi ra console để kiểm tra
            }
            
            request.getRequestDispatcher("Thankyou.jsp").forward(request, response);
        } else {
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
