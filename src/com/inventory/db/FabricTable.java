package com.inventory.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class FabricTable {

    public static void create(Connection con) {
        Statement stmt = null;

        String sql = "CREATE TABLE IF NOT EXISTS fabric (" +
                "fabric_id SERIAL PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "type VARCHAR(50) NOT NULL, " +
                "color VARCHAR(50), " +
                "gsm INT, " +
                "price NUMERIC(10,2), " +
                "supplier_id INT NOT NULL, " +
                "FOREIGN KEY (supplier_id) REFERENCES users(user_id)" +
                ");";

        try {
            stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("fabric table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating fabric table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.out.println("Error closing statement: " + ex.getMessage());
            }
        }
    }
}
