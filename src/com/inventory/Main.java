package com.inventory;

import com.inventory.repository.DBConnection;
import java.sql.Connection;
import com.inventory.repository.TableCreator;
import com.inventory.view.MainMenu;

public class Main {

    static {
        try{
            Connection conn= DBConnection.getInstance();
            System.out.println("DB CONNECT ESTABLISHED");

            TableCreator.createAllTables();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {




        System.out.println(" WELCOME TO INVENTORY MANAGEMENT SYSTEM");

        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}