package com.inventory.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {

    private static Connection connection;
    private static String url;
    private static String username;
    private static String password;

    static {

        InputStream input=null;

        try{
            ClassLoader loader=DBConnection.class.getClassLoader();
            String filename="db.properties";
            input=loader.getResourceAsStream(filename);

            if(input==null){
                throw new RuntimeException("db.properties file not exits");
            }

            Properties prop=new Properties();
            prop.load(input);

            url=prop.getProperty("db.url");
            username=prop.getProperty("db.username");
            password=prop.getProperty("db.password");
        }

        catch (Exception e){
            System.out.println("Error loading DB config: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            if(input!=null){
                try{
                    input.close();
                }
                catch (Exception ex){
                    System.out.println("Error closing input stream: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

    private DBConnection(){

    }

    public static Connection getInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
