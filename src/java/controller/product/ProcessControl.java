/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.product;

import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author PC
 */
@WebServlet(name = "ProcessControl", urlPatterns = {"/process"})
public class ProcessControl extends HttpServlet {

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
            out.println("<title>Servlet ProcessControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessControl at " + request.getContextPath() + "</h1>");
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
        ProductDAO dao = new ProductDAO();
        List<Product> listP = dao.getAllProduct();
        Cookie[] cookies = request.getCookies();
        String txt = "";

        if (cookies != null) {
            for (Cookie o : cookies) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    o.setMaxAge(0); // Xóa cookie cũ
                    response.addCookie(o);
                }
            }
        }

        Cart cart = new Cart(txt, listP);
        String num_raw = request.getParameter("num");
        String id_raw = request.getParameter("id");
        String id_delete = request.getParameter("delete");

        int id, num = 0;

        try {
            if (id_delete != null && !id_delete.isEmpty()) {
                int remove = Integer.parseInt(id_delete);
                cart.removeItem(remove); // Xóa sản phẩm khỏi giỏ hàng
            } else {
                id = Integer.parseInt(id_raw);
                num = Integer.parseInt(num_raw);
                Product p = dao.getProductById(id_raw);
                int numStore = p.getQuantity();

                if (num == -1 && (cart.getQuantityByID(id) <= 1)) {
                    cart.removeItem(id); // Nếu số lượng <=1 mà giảm thì xóa luôn
                } else {
                    if ((num == 1) && cart.getQuantityByID(id) >= numStore) {
                        num = 0; // Nếu vượt quá số lượng trong kho thì không tăng nữa
                    }
                    double price = p.getPrice() * 2;
                    Item t = new Item(p, num, price);
                    cart.addItem(t);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

// Tạo lại cookie mới với dữ liệu đã cập nhật
        List<Item> items = cart.getItems();
        txt = "";

        if (!items.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(items.get(0).getProduct().getpId()).append(":").append(items.get(0).getQuantity());

            for (int i = 1; i < items.size(); i++) {
                sb.append("|").append(items.get(i).getProduct().getpId()).append(":").append(items.get(i).getQuantity());
            }
            txt = sb.toString();
        }

        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(24 * 60 * 60);
        response.addCookie(c);

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
        
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

        ProductDAO dao = new ProductDAO();
        List<Product> listP = dao.getAllProduct();
        Cookie[] cookies = request.getCookies();
        String txt = "";

        if (cookies != null) {
            for (Cookie o : cookies) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    o.setMaxAge(0); // Xóa cookie cũ
                    response.addCookie(o);
                }
            }
        }

        Cart cart = new Cart(txt, listP);
        String num_raw = request.getParameter("num");
        String id_raw = request.getParameter("id");
        String id_delete = request.getParameter("delete");

        int id, num = 0;

        try {
            if (id_delete != null && !id_delete.isEmpty()) {
                int remove = Integer.parseInt(id_delete);
                cart.removeItem(remove); // Xóa sản phẩm khỏi giỏ hàng
            } else {
                id = Integer.parseInt(id_raw);
                num = Integer.parseInt(num_raw);
                Product p = dao.getProductById(id_raw);
                int numStore = p.getQuantity();

                if (num == -1 && (cart.getQuantityByID(id) <= 1)) {
                    cart.removeItem(id); // Nếu số lượng <=1 mà giảm thì xóa luôn
                } else {
                    if ((num == 1) && cart.getQuantityByID(id) >= numStore) {
                        num = 0; // Nếu vượt quá số lượng trong kho thì không tăng nữa
                    }
                    double price = p.getPrice() * 2;
                    Item t = new Item(p, num, price);
                    cart.addItem(t);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

// Tạo lại cookie mới với dữ liệu đã cập nhật
        List<Item> items = cart.getItems();
        txt = "";

        if (!items.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(items.get(0).getProduct().getpId()).append(":").append(items.get(0).getQuantity());

            for (int i = 1; i < items.size(); i++) {
                sb.append("|").append(items.get(i).getProduct().getpId()).append(":").append(items.get(i).getQuantity());
            }
            txt = sb.toString();
        }

        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(24 * 60 * 60);
        response.addCookie(c);

        request.setAttribute("cart", cart);
        request.getRequestDispatcher("Cart.jsp").forward(request, response);
        
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
