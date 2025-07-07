package com.inventory.db;

import java.sql.Connection;
import java.sql.Statement;

public class ProductionRequestTable {

    public static void create(Connection con) {
        Statement stmt = null;

        try {
            stmt = con.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS production_request (" +
                    "request_id SERIAL PRIMARY KEY, " +
                    "inventory_id INT NOT NULL, " +
                    "quantity INT NOT NULL CHECK (quantity > 0), " +
                    "status VARCHAR(20) DEFAULT 'PENDING', " +
                    "request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (inventory_id) REFERENCES inventory(inventory_id) ON DELETE CASCADE" +
                    ")";

            stmt.executeUpdate(sql);
            System.out.println("production_request table created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating production_request table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception ignored) {}
        }
    }
}
