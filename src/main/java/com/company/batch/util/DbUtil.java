package com.company.batch.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/customerdb?reWriteBatchedInserts=true";
    private static final String DEFAULT_USER = "postgres";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("PostgreSQL JDBC driver not found", e);
        }

        String url = System.getenv().getOrDefault("DB_URL", DEFAULT_URL);
        String user = System.getenv().getOrDefault("DB_USER", DEFAULT_USER);
        String password = System.getenv().getOrDefault("DB_PASSWORD", "");

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("reWriteBatchedInserts", "true");

        Connection connection = DriverManager.getConnection(url, props);
        connection.setAutoCommit(false);
        return connection;
    }
}
