package com.company.batch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatusMasterDao {
    private static final String SQL_SELECT = "SELECT status_code, status_name FROM customer_status_master";
    private final Connection connection;

    public StatusMasterDao(Connection connection) {
        this.connection = connection;
    }

    public Map<String, String> loadStatusMap() throws SQLException {
        Map<String, String> statusMap = new HashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL_SELECT);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                statusMap.put(rs.getString("status_code"), rs.getString("status_name"));
            }
        }
        return statusMap;
    }
}
