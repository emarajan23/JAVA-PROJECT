package com.inventory.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PurchaseTable {

    public static void create(Connection con) {
        Statement stmt = null;

        String sql = "CREATE TABLE IF NOT EXISTS purchase ("
                + "purchase_id SERIAL PRIMARY KEY, "
                + "fabric_id INT NOT NULL, "
                + "quantity INT NOT NULL CHECK(quantity > 0), "
                + "amount DOUBLE PRECISION NOT NULL CHECK(amount >= 0), "
                + "purchase_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, "
                + "FOREIGN KEY (fabric_id) REFERENCES fabric(fabric_id) ON DELETE CASCADE"
                + ")";

        try {
            stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("Purchase table created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating purchase table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException ex) {
                System.out.println(" Error closing statement: " + ex.getMessage());
            }
        }
    }
}
