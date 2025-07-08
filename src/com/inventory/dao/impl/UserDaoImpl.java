package com.inventory.dao.impl;

import com.inventory.dao.UserDao;
import com.inventory.repository.DBConnection;
import com.inventory.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    @Override
    public int saveUser(User user) {
        int userId = -1;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getInstance();
            String sql = "INSERT INTO users(name, email, contact_number, role) VALUES (?, ?, ?, ?) RETURNING user_id";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getContactNumber());
            ps.setString(4, user.getRole().name());

            rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("user_id");
            }
        } catch (Exception e) {
            e.printStackTrace(); // You may want to rethrow this or log it
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return userId;
    }
}
