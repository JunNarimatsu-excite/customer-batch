package com.company.batch.dao;

import com.company.batch.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerDao {
    private static final String SQL_INSERT = "INSERT INTO processed_customers (customer_id, customer_name, email, status_code, status_name) VALUES (?, ?, ?, ?, ?)";
    private final Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    public void batchInsert(List<Customer> customers) throws SQLException {
        if (customers == null || customers.isEmpty()) {
            return;
        }

        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            for (Customer customer : customers) {
                ps.setInt(1, customer.getCustomerId());
                ps.setString(2, customer.getCustomerName());
                ps.setString(3, customer.getEmail());
                ps.setString(4, customer.getStatusCode());
                ps.setString(5, customer.getStatusName());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
