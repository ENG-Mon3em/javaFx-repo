package com.example.javafxproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {

    public static Connection DBConnection() {

        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "MON3EM", "9718395");

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return conn;

    }
}