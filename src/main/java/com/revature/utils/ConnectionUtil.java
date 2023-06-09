package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static Connection conn = null;

    private ConnectionUtil() {};

    public static Connection getConnection() throws SQLException {

        if (conn != null && !conn.isClosed()) {
            System.out.println("Connection already open");
            return conn;
        };

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not find postgresql driver!");
        };

        String url = System.getenv("URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");

        conn = DriverManager.getConnection(url, username, password);

        return conn;

    };

};
