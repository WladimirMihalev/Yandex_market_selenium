package org.example;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class GeneratorCSV {

    String[] HEADER = {"Category Name"};

    public void createCSVFile(List<String> categories) throws IOException {
        FileWriter out = new FileWriter("book_new.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(HEADER))) {
            categories.forEach(title -> {
                try {
                    printer.printRecord(title);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
