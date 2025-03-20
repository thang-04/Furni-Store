/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Category;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.DBContext;

/**
 *
 * @author PC
 */
public class ProductDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Category";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getAllProductByUid(String uid) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product where sell_Id =?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getProductById(String id) {
        String sql = "select * from Product where productID=? ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product c = new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getInt(9));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Product> searchProduct(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Product where productName like ? ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return list;
    }

    public List<Product> filterProducts(double minPrice, double maxPrice, List<Integer> brands, boolean isAll, String sortOrder) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE price BETWEEN ? AND ?";

        if (!brands.isEmpty()) {
            String brandPlaceholders = String.join(",", Collections.nCopies(brands.size(), "?"));
            sql += " AND categoryID IN (" + brandPlaceholders + ")";
        }
        if ("asc".equals(sortOrder)) {
            sql += " ORDER BY price ASC";
        } else if ("desc".equals(sortOrder)) {
            sql += " ORDER BY price DESC";
        }

        try {
            ps = connection.prepareStatement(sql.toString());
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);

            int index = 3;
            if (!isAll && !brands.isEmpty()) {
                for (Integer brand : brands) {
                    ps.setInt(index++, brand);
                }
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("productID"),
                        rs.getString("productName"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("quantity"),
                        rs.getDate("importDate"),
                        rs.getInt("categoryID"),
                        rs.getInt("sell_Id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteProduct(String pId) {
        String sql = "delete from Product where [productID]=? ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, pId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void addProduct(String productName, String image, double price, String description, int quantity, Date importDate, int categoryID, int sell_Id) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([productName]\n"
                + "           ,[image]\n"
                + "           ,[price]\n"
                + "           ,[description]\n"
                + "           ,[quantity]\n"
                + "           ,[importDate]\n"
                + "           ,[categoryID]\n"
                + "           ,[sell_Id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, productName);
            ps.setString(2, image);
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setInt(5, quantity);
            ps.setDate(6, new java.sql.Date(importDate.getTime()));
            ps.setInt(7, categoryID);
            ps.setInt(8, sell_Id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void updateProduct(String productName, String image, double price, String description, int quantity, Date importDate, int categoryID, int sell_Id, int pId) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [productName] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[importDate] = ?\n"
                + "      ,[categoryID] = ?\n"
                + "      ,[sell_Id] = ?\n"
                + " WHERE productID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, productName);
            ps.setString(2, image);
            ps.setDouble(3, price);
            ps.setString(4, description);
            ps.setInt(5, quantity);
            ps.setDate(6, new java.sql.Date(importDate.getTime()));
            ps.setInt(7, categoryID);
            ps.setInt(8, sell_Id);
            ps.setInt(9, pId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public int countTotalProducts() {
        String sql = "SELECT COUNT(*) FROM Product";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countTotalProductsByUid(String uid) {
        String sql = "SELECT COUNT(*) FROM Product WHERE sell_Id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, uid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countTotalSearchProducts(String txtSearch) {
        try {
            String query = "SELECT COUNT(*) FROM Product WHERE productName LIKE ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }

    public List<Product> getProductsByPage(int page, int pageSize) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product ORDER BY productID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * pageSize);
            ps.setInt(2, pageSize);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> getProductsByPageByUid(int page, int pageSize, String uid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE sell_Id = ? ORDER BY productID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> searchProductWithPaging(String txtSearch, int page, int pageSize) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE productName LIKE ? ORDER BY productID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, "%" + txtSearch + "%"); // Nếu productName là NVARCHAR, cần kiểm tra
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Product(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            rs.getString(5),
                            rs.getInt(6)
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return list;
    }

    public void updateQuantityProduct(Product product, int value) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [quantity] = (quantity - ?)\n"
                    + " WHERE productName=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, value);
            ps.setString(2, product.getpName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
