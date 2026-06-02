package com.example.util;

import com.example.model.Customer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderUtil {
    public static List<Customer> read(File file) {
        List<Customer> list = new ArrayList<>();
        if (file == null || !file.exists()) {
            return list;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // header
            while ((line = br.readLine()) != null) {
                String[] cols = line.split(",", -1);
                String id = cols.length > 0 ? cols[0].trim() : "";
                String name = cols.length > 1 ? cols[1].trim() : "";
                String status = cols.length > 2 ? cols[2].trim() : "";
                Customer c = new Customer(id, name, status);
                list.add(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
