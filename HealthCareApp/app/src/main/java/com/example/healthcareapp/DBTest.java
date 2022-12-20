package com.example.healthcareapp;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {
    public static void testConnection() {
        Connection connection = null;
        try {
            String DB_URL = "jdbc:mysql://www.db4free.net/healthcareappdb";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, "healthcareadmin", "Fe5AbXiZxLI2@7ylJ7nd");
            Log.d("DBTest", "Connection Successful");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    Log.d("DBTest", "Connection Closed");
                } catch (Exception e) {

                }
            }
        }
    }
}
