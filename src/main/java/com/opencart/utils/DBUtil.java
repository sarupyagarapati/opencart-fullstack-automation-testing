package com.opencart.utils;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

    public static Connection conn;
    public static Statement stmt;
    public static ResultSet rs;
    public static Properties prop;

    // 1. Establish Connection
    public static void connectToDB() {
        try {
            // Load Config
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(ip);

            // Connect to MySQL
            // (Standard XAMPP user is 'root' with no password)
            conn = DriverManager.getConnection(
                    prop.getProperty("db_url"), 
                    prop.getProperty("db_username"), 
                    prop.getProperty("db_password"));
            
            stmt = conn.createStatement();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ DB Connection Failed!");
        }
    }

    // 2. Execute Query (e.g., SELECT * FROM oc_customer)
    public static boolean isUserPresent(String email) {
        boolean isFound = false;
        try {
            connectToDB();
            // Note: OpenCart tables usually have prefix 'oc_'. Adjust if yours is different.
            String query = "SELECT * FROM oc_customer WHERE email = '" + email + "'";
            rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                isFound = true; // If we found a row, the user exists
            }
            
            closeDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isFound;
    }

    // 3. Close Connection
    public static void closeDB() {
        try {
            if (conn != null) conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}