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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author PC
 */
@WebServlet(name = "FilterControl", urlPatterns = {"/filter"})
public class FilterControl extends HttpServlet {

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
            out.println("<title>Servlet FilterControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterControl at " + request.getContextPath() + "</h1>");
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

        String[] brandParams = request.getParameterValues("brand");
        double minPrice = Double.parseDouble(request.getParameter("minPrice"));
        double maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder != null) {
            sortOrder = sortOrder.trim();
        } else {
            sortOrder = "";
        }
        int page = 1;
        int pageSize = 6; 
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.trim().isEmpty()) {
            try {
                page = Integer.parseInt(pageStr.trim());
                if (page < 1) {
                    page = 1; 
                }
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        List<Integer> brands = new ArrayList<>();
        boolean isAll = false;
        if (brandParams != null) {
            for (String b : brandParams) {
                String brandValue = b.trim();
                if (brandValue.equals("all")) {
                    isAll = true;
                    break;
                }
                try {
                    brands.add(Integer.parseInt(brandValue));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid brand value: " + brandValue);
                }
            }
        }

        ProductDAO productDAO = new ProductDAO();
        List<Product> allFilteredProducts = productDAO.filterProducts(minPrice, maxPrice, brands, isAll, sortOrder);
        int totalProducts = allFilteredProducts.size();
        int totalPages = Math.max(1, (int) Math.ceil((double) totalProducts / pageSize)); 

        if (page > totalPages) {
            page = totalPages;
        }
        List<Product> paginatedFilteredProducts = new ArrayList<>();
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalProducts);

        if (startIndex < totalProducts) {
            paginatedFilteredProducts = allFilteredProducts.subList(startIndex, endIndex);
        }

        List<Category> listC = productDAO.getAllCategory();

        request.setAttribute("minPrice", minPrice);
        request.setAttribute("maxPrice", maxPrice);
        request.setAttribute("brands", brands);
        request.setAttribute("isAll", isAll);
        request.setAttribute("sortOrder", sortOrder); 

        request.setAttribute("listC", listC);
        request.setAttribute("listP", paginatedFilteredProducts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentSortOrder", sortOrder);
        request.setAttribute("filteredProducts", true); 

        request.getRequestDispatcher("Shop.jsp").forward(request, response);
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
