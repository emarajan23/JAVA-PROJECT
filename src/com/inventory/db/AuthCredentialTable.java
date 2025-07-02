package com.inventory.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthCredentialTable {

    public static void create(Connection con){

        Statement stmt=null;

        String query = "CREATE TABLE IF NOT EXISTS auth_credential (" +
                "user_id INTEGER PRIMARY KEY, " +
                "username VARCHAR UNIQUE NOT NULL, " +
                "password_hash TEXT NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE" +
                ");";

        try{
            stmt=con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Authentication table created");
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
