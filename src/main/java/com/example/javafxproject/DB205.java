package com.example.javafxproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB205 {


    public static Connection dbconn(){
        Connection con = null;
        try {
// keep your existing connection string
            con = DriverManager.getConnection("jdbc:oracle:thin:DB205/DB205@localhost:1521/XE");
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Fail to connect");
        }
        return con;
    }
}