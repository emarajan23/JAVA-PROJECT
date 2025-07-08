package com.inventory.dao.impl;

import com.inventory.dao.FabricDao;
import com.inventory.model.Fabric;
import com.inventory.db.DBConnection;
import com.inventory.model.FabricEntity;
import com.inventory.model.UserRole;
import com.inventory.model.UserEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FabricDaoImpl implements FabricDao {

    @Override
    public void saveFabric(Fabric fabric) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO fabric (name, type, color, gsm, price, supplier_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            con = DBConnection.getInstance();
            ps = con.prepareStatement(sql);

            ps.setString(1, fabric.getName());
            ps.setString(2, fabric.getType());
            ps.setString(3, fabric.getColor());
            ps.setInt(4, fabric.getGsm());
            ps.setDouble(5, fabric.getPrice());
            ps.setInt(6, fabric.getSupplier().getUserId());

            ps.executeUpdate();
            System.out.println("Fabric saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving fabric: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.out.println("Error closing PreparedStatement: " + e.getMessage());
            }

            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing Connection: " + e.getMessage());
            }
        }
    }
    @Override
    public List<FabricEntity> getAllFabrics() {
        List<FabricEntity> fabricList = new ArrayList<>();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getInstance();
            stmt = con.createStatement();

            String sql = "SELECT f.fabric_id, f.name, f.type, f.color, f.gsm, f.price, " +
                    "u.user_id, u.name AS supplier_name, u.email, u.contact_number, u.role " +
                    "FROM fabric f JOIN users u ON f.supplier_id = u.user_id";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int fabricId = rs.getInt("fabric_id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String color = rs.getString("color");
                int gsm = rs.getInt("gsm");
                double price = rs.getDouble("price");

                // Construct Users object using constructor
                UserEntity supplier = new UserEntity(
                        rs.getInt("user_id"),
                        rs.getString("supplier_name"),
                        rs.getString("email"),
                        rs.getString("contact_number"),
                        UserRole.valueOf(rs.getString("role"))
                );


                FabricEntity fabric = new FabricEntity(fabricId, name, type, color, gsm, price, supplier);
                fabricList.add(fabric);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return fabricList;
    }
}
