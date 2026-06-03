package com.company.batch.service;

import com.company.batch.dao.CustomerDao;
import com.company.batch.dao.StatusMasterDao;
import com.company.batch.model.Customer;
import com.company.batch.util.CsvReaderUtil;
import com.company.batch.util.DbUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CustomerImportService {
    private static final String CSV_PATH = "sample/customer.csv";

    public void execute() {
        long start = System.nanoTime();
        try (Connection conn = DbUtil.getConnection()) {
            conn.setAutoCommit(false);

            Map<String, String> statusMap = new StatusMasterDao(conn).loadStatusMap();
            List<Customer> customers = CsvReaderUtil.read(new File(CSV_PATH));

            for (Customer customer : customers) {
                String statusName = statusMap.get(customer.getStatusCode());
                customer.setStatusName(statusName == null ? "UNKNOWN" : statusName);
            }

            try {
                new CustomerDao(conn).batchInsert(customers);
                conn.commit();
                System.out.println("Batch import completed: " + customers.size() + " records inserted.");
                double elapsedSeconds = (System.nanoTime() - start) / 1_000_000_000.0;
                System.out.printf("Elapsed time: %.3f seconds%n", elapsedSeconds);
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        } catch (Exception e) {
            System.err.println("Batch import failed: " + e.getMessage());
            e.printStackTrace(System.err);
        }
    }
}
