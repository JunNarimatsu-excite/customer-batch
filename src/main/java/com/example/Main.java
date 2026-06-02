package com.example;

import com.example.service.CustomerImportService;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String csv = "sample/customer.csv";
        CustomerImportService service = new CustomerImportService();
        service.importFromCsv(Paths.get(csv).toFile());
    }
}
