package com.company.batch;

import com.company.batch.service.CustomerImportService;

public class Main {
    public static void main(String[] args) {
        CustomerImportService service = new CustomerImportService();
        service.execute();
    }
}
