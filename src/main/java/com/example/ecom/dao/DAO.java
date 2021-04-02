package com.example.ecom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    public static Connection conn;
    private static String DB_NAME = "ecom";
    private static String username = "root";
    private static String password = "root";
    private static String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    public DAO(){
        if(conn == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL,username,password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Connect database successfully");
        }
    }
}
