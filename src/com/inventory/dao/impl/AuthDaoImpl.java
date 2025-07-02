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
        try (Connection con = DBConnection.getInstance()) {
            String sql = "INSERT INTO auth_credential(user_id, username, password) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        boolean exists = false;
        try (Connection con = DBConnection.getInstance()) {
            String sql = "SELECT 1 FROM auth_credential WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public Users fetchUserByCredentials(String username, String password) {
        Users user = null;

        try (Connection con = DBConnection.getInstance()) {
            String sql = "SELECT u.user_id, u.name, u.email, u.contact_number, u.role " +
                    "FROM auth_credential a JOIN users u ON a.user_id = u.user_id " +
                    "WHERE a.username = ? AND a.password = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Users();
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setContactNumber(rs.getString("contact_number"));
                user.setRole(UserRole.valueOf(rs.getString("role")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

}
