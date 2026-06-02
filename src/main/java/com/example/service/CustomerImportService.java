package com.example.service;

import com.example.model.Customer;
import com.example.dao.CustomerDao;
import com.example.util.CsvReaderUtil;

import java.io.File;
import java.util.List;

public class CustomerImportService {
    private final CustomerDao dao = new CustomerDao();

    public void importFromCsv(File csvFile) {
        List<Customer> customers = CsvReaderUtil.read(csvFile);
        for (Customer c : customers) {
            dao.save(c);
        }
    }
}
