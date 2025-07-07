package com.inventory.dao.impl;

import com.inventory.dao.RequestDao;
import com.inventory.db.DBConnection;
import com.inventory.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDaoImpl implements RequestDao {

    @Override
    public boolean createRequest(int inventoryId, int quantity) {
        String sql = "INSERT INTO production_request (inventory_id, quantity, status, request_date) VALUES (?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getInstance();
            ps = con.prepareStatement(sql);
            ps.setInt(1, inventoryId);
            ps.setInt(2, quantity);
            ps.setString(3, RequestStatus.PENDING.name());
            ps.setTimestamp(4, Timestamp.valueOf(java.time.LocalDateTime.now()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }

        return false;
    }

    @Override
    public List<ProductionRequest> getPendingRequests() {
        List<ProductionRequest> list = new ArrayList<>();

        String sql = "SELECT r.request_id, r.inventory_id, r.quantity, r.status, r.request_date, " +
                "i.quantity AS stock_qty, f.fabric_id, f.name, f.type, f.color, f.gsm, f.price " +
                "FROM production_request r " +
                "JOIN inventory i ON r.inventory_id = i.inventory_id " +
                "JOIN fabric f ON i.fabric_id = f.fabric_id " +
                "WHERE r.status = 'PENDING'";

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
                        rs.getInt("stock_qty")
                );

                ProductionRequest req = new ProductionRequest(
                        rs.getInt("request_id"),
                        item,
                        rs.getInt("quantity"),
                        RequestStatus.valueOf(rs.getString("status")),
                        rs.getTimestamp("request_date").toLocalDateTime()
                );

                list.add(req);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }

        return list;
    }

    @Override
    public boolean updateRequestStatus(int requestId, String status) {
        String sql = "UPDATE production_request SET status = ? WHERE request_id = ?";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBConnection.getInstance();
            ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, requestId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }

        return false;
    }

    @Override
    public ProductionRequest getRequestById(int requestId) {
        String sql = "SELECT r.request_id, r.inventory_id, r.quantity, r.status, r.request_date, " +
                "i.quantity AS stock_qty, f.fabric_id, f.name, f.type, f.color, f.gsm, f.price " +
                "FROM production_request r " +
                "JOIN inventory i ON r.inventory_id = i.inventory_id " +
                "JOIN fabric f ON i.fabric_id = f.fabric_id " +
                "WHERE r.request_id = ?";

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductionRequest request = null;

        try {
            con = DBConnection.getInstance();
            ps = con.prepareStatement(sql);
            ps.setInt(1, requestId);
            rs = ps.executeQuery();

            if (rs.next()) {
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
                        rs.getInt("stock_qty")
                );

                request = new ProductionRequest(
                        rs.getInt("request_id"),
                        item,
                        rs.getInt("quantity"),
                        RequestStatus.valueOf(rs.getString("status")),
                        rs.getTimestamp("request_date").toLocalDateTime()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }

        return request;
    }
}
