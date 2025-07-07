package com.inventory.db;

import java.sql.Connection;
import java.sql.Statement;

public class InventoryTable {
    public static void create(Connection con) {
        try (Statement stmt = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS inventory (" +
                    "inventory_id SERIAL PRIMARY KEY, " +
                    "fabric_id INT UNIQUE NOT NULL, " +
                    "quantity INT NOT NULL CHECK (quantity >= 0), " +
                    "FOREIGN KEY (fabric_id) REFERENCES fabric(fabric_id) ON DELETE CASCADE" +
                    ")";
            stmt.executeUpdate(sql);
            System.out.println("Inventory table created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating inventory table: " + e.getMessage());
        }
    }
}
