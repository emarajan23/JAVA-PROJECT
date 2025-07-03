package com.inventory.dao.impl;

import com.inventory.dao.AuthDao;
import com.inventory.db.DBConnection;
import com.inventory.model.UserRole;
import com.inventory.model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDaoImpl implements AuthDao {

    @Override
    public void saveCredentials(int userId, String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getInstance();
            String sql = "INSERT INTO auth_credential(user_id, username, password) VALUES (?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            con = DBConnection.getInstance();
            String sql = "SELECT 1 FROM auth_credential WHERE username = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            exists = rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return exists;
    }

    @Override
    public Users fetchUserByCredentials(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Users user = null;

        try {
            con = DBConnection.getInstance();
            String sql = "SELECT u.user_id, u.name, u.email, u.contact_number, u.role " +
                    "FROM auth_credential a " +
                    "JOIN users u ON a.user_id = u.user_id " +
                    "WHERE a.username = ? AND a.password = ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new Users(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("contact_number"),
                        UserRole.valueOf(rs.getString("role"))
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return user;
    }
}
