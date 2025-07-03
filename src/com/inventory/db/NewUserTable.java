package com.inventory.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NewUserTable {

    public static void create(Connection con){

        Statement stmt=null;

        String query = "CREATE TABLE IF NOT EXISTS USERS (" +
                "user_id SERIAL PRIMARY KEY," +
                "name VARCHAR," +
                "email VARCHAR UNIQUE NOT NULL," +
                "contact_number VARCHAR," +
                "role VARCHAR NOT NULL CHECK(role IN('ADMIN','SUPPLIER','INVENTORY_MANAGER','PRODUCTION_MANAGER')))";

        try{
            stmt=con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("user table created");
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        finally{
            if(stmt!=null){
                try {
                    stmt.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }

            }
        }


    }

}
