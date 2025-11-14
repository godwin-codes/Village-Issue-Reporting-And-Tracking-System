package com.village.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    // Database configuration variables (will be populated from environment or config.properties)
    private static String DB_HOST = System.getenv("DB_HOST");
    private static String DB_PORT = System.getenv("DB_PORT");
    private static String DB_NAME = System.getenv("DB_NAME");
    private static String USER = System.getenv("DB_USER");
    private static String PASSWORD = System.getenv("DB_PASSWORD");
    private static String URL;

    static {
        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found! Please check your MySQL connector JAR file.");
            e.printStackTrace();
        }

        // If environment variables are not provided, try loading from config.properties
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            System.out.println("Loaded DB configuration from config.properties");
        } catch (IOException e) {
            // It's fine if the file doesn't exist — we'll rely on environment variables or defaults
        }

        if (DB_HOST == null || DB_HOST.isEmpty()) {
            DB_HOST = props.getProperty("db.host", "localhost");
        }
        if (DB_PORT == null || DB_PORT.isEmpty()) {
            DB_PORT = props.getProperty("db.port", "3306");
        }
        if (DB_NAME == null || DB_NAME.isEmpty()) {
            DB_NAME = props.getProperty("db.name", "village_issue_system");
        }
        if (USER == null || USER.isEmpty()) {
            USER = props.getProperty("db.user", "root");
        }
        if (PASSWORD == null) {
            PASSWORD = props.getProperty("db.password", "");
        }

        URL = String.format(
                "jdbc:mysql://%s:%s/%s?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true",
                DB_HOST, DB_PORT, DB_NAME
        );
    }

    // Always return a new connection
    public static Connection getConnection() {
        try {
            if (PASSWORD == null || PASSWORD.isEmpty()) {
                System.err.println("Warning: DB_PASSWORD is not set. Attempting connection with empty password.");
            }

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Check validity of the connection (2-second timeout)
            if (conn != null && conn.isValid(2)) {
                System.out.println("Database connected successfully!");
            } else {
                System.err.println("Connection obtained but not valid!");
            }

            return conn;
        } catch (SQLException e) {
            System.err.println("Failed to connect to database! URL=" + URL + " user=" + USER);
            e.printStackTrace();
        }
        return null;
    }

    // Test method
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Connection test successful!");
            } else {
                System.err.println("Connection test failed!");
            }
        } catch (SQLException e) {
            System.err.println("Error while testing connection:");
            e.printStackTrace();
        }
    }
}
