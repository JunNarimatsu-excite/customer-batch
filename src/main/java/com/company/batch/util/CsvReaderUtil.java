package com.company.batch.util;

import com.company.batch.model.Customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderUtil {
    public static List<Customer> read(File file) {
        List<Customer> customers = new ArrayList<>();
        if (file == null || !file.exists()) {
            System.err.println("CSV file not found: " + file);
            return customers;
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            String line = reader.readLine(); // header
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] fields = line.split(",", -1);
                if (fields.length < 4) {
                    System.err.println("Invalid CSV row (missing columns): " + line);
                    continue;
                }

                int customerId;
                try {
                    customerId = Integer.parseInt(fields[0].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid customer_id value: " + fields[0]);
                    continue;
                }

                String customerName = fields[1].trim();
                String email        = fields[2].trim();
                String statusCode   = fields[3].trim();

                if (!isValidEmail(email)) {
                    System.err.println("Invalid email address: " + email + " for customer_id=" + customerId);
                    continue;
                }

                customers.add(new Customer(customerId, customerName, email, statusCode));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file", e);
        }

        return customers;
    }

    private static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
