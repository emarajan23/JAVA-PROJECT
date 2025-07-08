package com.inventory.dao.impl;

import com.inventory.dao.InventoryDao;
import com.inventory.repository.DBConnection;
import com.inventory.model.FabricEntity;
import com.inventory.model.InventoryItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class InventoryDaoImpl implements InventoryDao {

    @Override
    public void addOrUpdateInventory(int fabricId, int quantity) {
        Connection con = null;
        PreparedStatement selectStmt = null;
        ResultSet rs = null;

        String selectQuery = "SELECT quantity FROM inventory WHERE fabric_id = ?";
        String updateQuery = "UPDATE inventory SET quantity = quantity + ? WHERE fabric_id = ?";
        String insertQuery = "INSERT INTO inventory (fabric_id, quantity) VALUES (?, ?)";

        try {
            con = DBConnection.getInstance();
            selectStmt = con.prepareStatement(selectQuery);
            selectStmt.setInt(1, fabricId);
            rs = selectStmt.executeQuery();

            if (rs.next()) {
                PreparedStatement updateStmt = null;
                try {
                    updateStmt = con.prepareStatement(updateQuery);
                    updateStmt.setInt(1, quantity);
                    updateStmt.setInt(2, fabricId);
                    updateStmt.executeUpdate();
                } finally {
                    if (updateStmt != null) {
                        try {
                            updateStmt.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                PreparedStatement insertStmt = null;
                try {
                    insertStmt = con.prepareStatement(insertQuery);
                    insertStmt.setInt(1, fabricId);
                    insertStmt.setInt(2, quantity);
                    insertStmt.executeUpdate();
                } finally {
                    if (insertStmt != null) {
                        try {
                            insertStmt.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (Exception ignored) {}

            try {
                if (selectStmt != null) selectStmt.close();
            } catch (Exception ignored) {}

            try {
                if (con != null) con.close();
            } catch (Exception ignored) {}
        }
    }

    @Override
    public List<InventoryItem> getAllInventory() {
        List<InventoryItem> inventoryList = new ArrayList<>();

        String sql = "SELECT i.inventory_id, i.fabric_id, i.quantity, " +
                "f.name, f.type, f.color, f.gsm, f.price " +
                "FROM inventory i " +
                "JOIN fabric f ON i.fabric_id = f.fabric_id";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getInstance();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                FabricEntity fabric = new FabricEntity(
                        rs.getInt("fabric_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("color"),
                        rs.getInt("gsm"),
                        rs.getDouble("price")
                );

                InventoryItem item = new InventoryItem(
                        rs.getInt("inventory_id"),
                        fabric,
                        rs.getInt("quantity")
                );

                inventoryList.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }

        return inventoryList;
    }

    @Override
    public List<InventoryItem> getLowInventoryItems() {
        List<InventoryItem> lowInventoryList = new ArrayList<>();

        String sql = "SELECT i.inventory_id, i.fabric_id, i.quantity, " +
                "f.name, f.type, f.color, f.gsm, f.price " +
                "FROM inventory i " +
                "JOIN fabric f ON i.fabric_id = f.fabric_id " +
                "WHERE i.quantity <= 5";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getInstance();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                FabricEntity fabric = new FabricEntity(
                        rs.getInt("fabric_id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("color"),
                        rs.getInt("gsm"),
                        rs.getDouble("price")
                );

                InventoryItem item = new InventoryItem(
                        rs.getInt("inventory_id"),
                        fabric,
                        rs.getInt("quantity")
                );

                lowInventoryList.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }

        return lowInventoryList;
    }

    @Override
    public void reduceInventoryQuantity(int inventoryId, int quantity) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "UPDATE inventory SET quantity = quantity - ? WHERE inventory_id = ? AND quantity >= ?";

        try {
            con = DBConnection.getInstance();
            ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, inventoryId);
            ps.setInt(3, quantity);
            int updated = ps.executeUpdate();

            if (updated == 0) {
                System.out.println(" Not enough stock to reduce.");
            } else {
                System.out.println(" Inventory reduced.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }


}
