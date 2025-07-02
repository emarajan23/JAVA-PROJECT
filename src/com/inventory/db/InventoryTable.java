package com.inventory.db;

import java.sql.Connection;
import java.sql.Statement;

public class InventoryTable {
    public static void create(Connection con) {
        try {
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS inventory (" +
                    "inventory_id SERIAL PRIMARY KEY," +
                    "fabric_id INT REFERENCES fabric(fabric_id) ON DELETE CASCADE," +
                    "quantity INT NOT NULL" +
                    ")";
            stmt.executeUpdate(sql);
            System.out.println("inventory table created successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
