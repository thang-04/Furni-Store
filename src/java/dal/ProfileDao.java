/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import model.DBContext;
import model.User;

/**
 *
 * @author PC
 */
public class ProfileDao extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void updateProfile(String userName, String fullName, String pass, int roleID, String image, String email, Date birthday, String address, String phone, String uId) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [userName] = ?\n"
                + "      ,[fullName] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[roleID] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[birthDay] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + " WHERE [userID]=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, fullName);
            ps.setString(3, pass);
            ps.setInt(4, roleID);
            ps.setString(5, image);
            ps.setString(6, email);
            ps.setDate(7, new java.sql.Date(birthday.getTime()));
            ps.setString(8, address);
            ps.setString(9, phone);
            ps.setString(10, uId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(String uId) {
        String sql = "SELECT * FROM Users where [userID]= ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, uId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("userID"),
                            rs.getString("userName"),
                            rs.getString("fullName"),
                            rs.getString("password"),
                            rs.getInt("roleID"),
                            rs.getString("address"),
                            rs.getDate("birthday"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("image")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
