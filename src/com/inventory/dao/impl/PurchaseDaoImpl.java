package com.inventory.dao.impl;

import com.inventory.dao.PurchaseDao;
import com.inventory.repository.DBConnection;
import com.inventory.model.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PurchaseDaoImpl implements PurchaseDao {

    @Override
    public void savePurchase(Purchase purchase) {
        String sql = "INSERT INTO purchase (fabric_id, quantity, amount) VALUES (?, ?, ?)";

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getInstance();
            ps = con.prepareStatement(sql);

            ps.setInt(1, purchase.getFabric().getFabricId());
            ps.setInt(2, purchase.getQuantity());
            ps.setDouble(3, purchase.getAmount());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving purchase: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Failed to close statement: " + e.getMessage());
            }
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Failed to close connection: " + e.getMessage());
            }
        }
    }
}
