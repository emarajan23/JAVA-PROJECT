package com.inventory.repository;

import java.sql.Connection;
import java.sql.SQLException;

public class TableCreator {


    public static void createAllTables(){
        Connection con=null;
        try {
             con = DBConnection.getInstance();

             NewUserTable.create(con);
             AuthCredentialTable.create(con);
             FabricTable.create(con);
             PurchaseTable.create(con);
             InventoryTable.create(con);
             ProductionRequestTable.create(con);



        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {

                try {
                    if(con !=null && !con.isClosed()) {
                        con.close();
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }

        }
    }
}
