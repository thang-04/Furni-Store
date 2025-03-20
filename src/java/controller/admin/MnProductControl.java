/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.ProductDAO;
import dal.ProfileDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author PC
 */
@WebServlet(name = "ManageProduct", urlPatterns = {"/manageProduct"})
public class MnProductControl extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet manageProductControl at " + request.getContextPath() + "</h1>");
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
        ProfileDao dao2 = new ProfileDao();
        List<Category> listC = dao.getAllCategory();
        List<Product> listProducts = null;
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uID");
        if (uid == null) {
            uid = request.getParameter("uID");
            session.setAttribute("uID", uid);
        }
        try {
            String indexParam = request.getParameter("index");
            int index = 1;
            if (indexParam != null) {
                try {
                    index = Integer.parseInt(indexParam);
                    if (index < 1) {
                        index = 1;
                    }
                } catch (NumberFormatException e) {
                }
            }
            int pageSize = 5;
            int totalProducts, totalPages;
            //check admin(uid=3)
            if (Integer.parseInt(uid) == 3) {
                totalProducts = dao.countTotalProducts();
                totalPages = (totalProducts % pageSize == 0)
                        ? (totalProducts / pageSize)
                        : (totalProducts / pageSize) + 1;
                if (index > totalPages && totalPages > 0) {
                    index = totalPages;
                }
                listProducts = dao.getProductsByPage(index, pageSize);
            } else {
                totalProducts = dao.countTotalProductsByUid(uid);
                totalPages = (totalProducts % pageSize == 0)
                        ? (totalProducts / pageSize)
                        : (totalProducts / pageSize) + 1;
                if (index > totalPages && totalPages > 0) {
                    index = totalPages;
                }
                listProducts = dao.getProductsByPageByUid(index, pageSize, uid);
            }

            request.setAttribute("listP", listProducts);
            request.setAttribute("currentPage", index);
            request.setAttribute("count", totalPages);
            request.setAttribute("totalProducts", totalProducts);

            int start = (index - 1) * pageSize + 1;
            int end = Math.min(index * pageSize, totalProducts);
            request.setAttribute("startItem", start);
            request.setAttribute("endItem", end);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("listC", listC);
//        request.setAttribute("listP", listP);
        request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
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
        processRequest(request, response);
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
