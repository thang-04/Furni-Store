/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import model.DBContext;

/**
 *
 * @author PC
 */
public class LoginDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /*-----------------------LOGIN and REGISTER--------------------------------*/
    public User login(String user, String pass) {
        String sql = "SELECT * FROM Users WHERE [userName] = ? AND [password] = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user);
            ps.setString(2, pass);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User checkAccExist(String user) {
        String sql = "select * from Users where [userName]=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void signUpNew(String userName, String fullName, String pass, int roleID, String image, String email, Date birthday, String address, String phone) {
        String sql = """
                 INSERT INTO [dbo].[Users]
                            ([userName]
                            ,[fullName]
                            ,[password]
                            ,[roleID]
                            ,[image]
                            ,[email]
                            ,[birthDay]
                            ,[address]
                            ,[phone])
                      VALUES
                            (?,?,?,?,?,?,?,?,?)""";
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

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*----------------------End LOGIN and REGISTER--------------------------------*/
}
