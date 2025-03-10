/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import model.Cart;
import model.DBContext;
import model.Item;
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
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            ps = connection.prepareStatement(sql1);
            ps.setString(1, date);
            ps.setInt(2, cus.getUid());
            ps.setDouble(3, cart.getTotalMoney());
            ps.setInt(4, 0);
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

}
