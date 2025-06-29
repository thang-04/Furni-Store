/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.DBContext;
import model.Item;
import model.Order;
import model.User;

/**
 *
 * @author PC
 */
public class OrderDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addOrder(User cus, Cart cart) {
        ProductDAO dao = new ProductDAO();
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql1 = "INSERT INTO [dbo].[Orders]\n"
                    + "           ([Date]\n"
                    + "           ,[userID]\n"
                    + "           ,[TotalMoney]\n"
                    + "           ,[status]\n"
                    + "           ,[isPay])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";
            ps = connection.prepareStatement(sql1);
            ps.setString(1, date);
            ps.setInt(2, cus.getUid());
            ps.setDouble(3, cart.getTotalMoney());
            ps.setInt(4, 0);
            ps.setInt(5, 0);

            ps.executeUpdate();

            //lay orderID cua order vua tao
            String sql2 = "SELECT Top 1 [OrderID] FROM [dbo].[Orders] ORDER BY [OrderID] DESC";
            PreparedStatement st2 = connection.prepareStatement(sql2);
            rs = st2.executeQuery();

            //add information to OrderDetail 
            if (rs.next()) {
                int oId = rs.getInt(1);
                for (Item item : cart.getItems()) {
                    String sql3 = "INSERT INTO [dbo].[OrderDetails]\n"
                            + "           ([OrderID]\n"
                            + "           ,[ProductID]\n"
                            + "           ,[Quantity]\n"
                            + "           ,[UnitPrice]\n"
                            + "           ,[Discount])\n"
                            + "     VALUES\n"
                            + "           (?,?,?,?,?)";
                    PreparedStatement st3 = connection.prepareStatement(sql3);
                    st3.setInt(1, oId);
                    st3.setInt(2, item.getProduct().getpId());
                    st3.setInt(3, item.getQuantity());
                    st3.setDouble(4, item.getProduct().getPrice());
                    //chua set discount nen tam de discount = 0
                    st3.setDouble(5, 0);
                    st3.executeUpdate();
                    dao.updateQuantityProduct(item.getProduct(), item.getQuantity());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getOrderById(int orderId) {
        Order order = null;
        String sql = "SELECT * FROM [dbo].[Orders] WHERE OrderID = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, orderId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setOId(rs.getInt("OrderID"));
                    order.setDate(rs.getTimestamp("Date"));
                    order.setUserId(rs.getInt("userID"));
                    order.setTotalMoney(rs.getDouble("TotalMoney"));
                    order.setStatus(rs.getInt("status"));
                    order.setIsPay(rs.getInt("isPay"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public int getLastOrderId() {
        int orderId = -1;
        String sql = "SELECT TOP 1 [OrderID] FROM [dbo].[Orders] ORDER BY [OrderID] DESC";

        try (PreparedStatement st = connection.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    // Get total number of orders
    public int getTotalOrders() {
        String sql = "SELECT COUNT(*) FROM Orders";
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

    // Get total revenue from Orders
    public double getTotalRevenue() {
        String sql = "SELECT SUM(TotalMoney) FROM Orders";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        String sql = "select *from Orders";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getAllOrderByUser(String uid) {
        List<Order> list = new ArrayList<>();
        String sql = "select *from Orders where userID=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getOrderBySeller(String uid) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT DISTINCT o.*\n"
                + "FROM Orders o\n"
                + "JOIN OrderDetails od ON o.OrderID = od.OrderID\n"
                + "JOIN Product p ON od.ProductID = p.productID\n"
                + "WHERE p.sell_Id = ? ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalUser() {
        String sql = "SELECT COUNT(*) FROM Users";
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

    public void updateStatus(int id) {
        String sql = "UPDATE [dbo].[Orders] SET [status] = 1 WHERE [OrderID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePaid(int id) {
        String sql = "UPDATE [dbo].[Orders] SET [isPay] = 1 WHERE [OrderID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
