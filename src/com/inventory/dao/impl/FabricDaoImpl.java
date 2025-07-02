package com.inventory.dao.impl;

import com.inventory.dao.FabricDao;
import com.inventory.db.DBConnection;
import com.inventory.model.Fabric;
import com.inventory.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FabricDaoImpl implements FabricDao {

    @Override
    public void saveFabric(Fabric fabric) {
        Connection con = null;
        PreparedStatement ps = null;

        String sql = "INSERT INTO fabric (name, type, color, gsm, rolls, price_per_roll, supplier_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = DBConnection.getInstance();
            ps = con.prepareStatement(sql);

            ps.setString(1, fabric.getName());
            ps.setString(2, fabric.getType());
            ps.setString(3, fabric.getColor());
            ps.setInt(4, fabric.getGsm());
            ps.setInt(5, fabric.getRolls());
            ps.setDouble(6, fabric.getPricePerRoll());
            ps.setInt(7, fabric.getSupplier().getUserId());

            ps.executeUpdate();
            System.out.println("Fabric saved successfully!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Fabric> getAllFabrics() {
        List<Fabric> fabrics = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM fabric";

        try {
            con = DBConnection.getInstance();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Fabric fabric = new Fabric();
                fabric.setFabricId(rs.getInt("fabric_id"));
                fabric.setName(rs.getString("name"));
                fabric.setType(rs.getString("type"));
                fabric.setColor(rs.getString("color"));
                fabric.setGsm(rs.getInt("gsm"));
                fabric.setRolls(rs.getInt("rolls"));
                fabric.setPricePerRoll(rs.getDouble("price_per_roll"));

                Users supplier = new Users();
                supplier.setUserId(rs.getInt("supplier_id"));
                fabric.setSupplier(supplier);

                fabrics.add(fabric);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return fabrics;
    }
}
