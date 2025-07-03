//package com.inventory.dao.impl;
//
//import com.inventory.dao.InventoryDao;
//import com.inventory.db.DBConnection;
//import com.inventory.model.InventoryItem;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class InventoryDaoImpl implements InventoryDao {
//
//    @Override
//    public void addOrUpdateInventory(int fabricId, int quantity) {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBConnection.getInstance();
//
//            String select = "SELECT * FROM inventory WHERE fabric_id = ?";
//            pstmt = con.prepareStatement(select);
//            pstmt.setInt(1, fabricId);
//            rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                String update = "UPDATE inventory SET quantity = quantity + ? WHERE fabric_id = ?";
//                stmt = con.prepareStatement(update);
//                stmt.setInt(1, quantity);
//                stmt.setInt(2, fabricId);
//                stmt.executeUpdate();
//            } else {
//                String insert = "INSERT INTO inventory (fabric_id, quantity) VALUES (?, ?)";
//                stmt = con.prepareStatement(insert);
//                stmt.setInt(1, fabricId);
//                stmt.setInt(2, quantity);
//                stmt.executeUpdate();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
//            try { if (pstmt != null) pstmt.close(); } catch (Exception ignored) {}
//            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
//            try { if (con != null) con.close(); } catch (Exception ignored) {}
//        }
//    }
//
//    @Override
//    public List<InventoryItem> getAllInventory() {
//        List<InventoryItem> items = new ArrayList<>();
//
//        Connection con = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBConnection.getInstance();
//            String sql = "SELECT i.*, f.name, f.type FROM inventory i JOIN fabric f ON i.fabric_id = f.fabric_id";
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//
//            while (rs.next()) {
//                InventoryItem item = new InventoryItem();
//                item.setInventoryId(rs.getInt("inventory_id"));
//                item.setFabricId(rs.getInt("fabric_id"));
//                item.setQuantity(rs.getInt("quantity"));
//                item.setFabricName(rs.getString("name"));
//                item.setFabricType(rs.getString("type"));
//                items.add(item);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
//            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
//            try { if (con != null) con.close(); } catch (Exception ignored) {}
//        }
//
//        return items;
//    }
//
//    @Override
//    public List<InventoryItem> getLowStockInventory(int threshold) {
//        List<InventoryItem> items = new ArrayList<>();
//
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBConnection.getInstance();
//            String sql = "SELECT i.*, f.name, f.type FROM inventory i " +
//                    "JOIN fabric f ON i.fabric_id = f.fabric_id " +
//                    "WHERE i.quantity < ?";
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, threshold);
//            rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                InventoryItem item = new InventoryItem();
//                item.setInventoryId(rs.getInt("inventory_id"));
//                item.setFabricId(rs.getInt("fabric_id"));
//                item.setQuantity(rs.getInt("quantity"));
//                item.setFabricName(rs.getString("name"));
//                item.setFabricType(rs.getString("type"));
//                items.add(item);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
//            try { if (pstmt != null) pstmt.close(); } catch (Exception ignored) {}
//            try { if (con != null) con.close(); } catch (Exception ignored) {}
//        }
//
//        return items;
//    }
//}
