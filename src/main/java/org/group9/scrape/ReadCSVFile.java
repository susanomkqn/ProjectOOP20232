package org.group9.scrape;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadCSVFile {
    public static void main(String[] args) {
        String[] header = {"Title", "URL", "Type", "Date", "Author", "DetailContents", "TagName", "KeyWord"};
        String csvFilePath = "DataBase.csv";

        try (FileReader fileReader = new FileReader(csvFilePath);
             CSVReader csvReader = new CSVReader(fileReader)) {

            // Read the header row
            String[] headerRow = csvReader.readNext();

            // Read the data rows
            List<String[]> dataRows = csvReader.readAll();

            // Process the data
            for (String[] row : dataRows) {
                for (int i = 0; i < header.length; i++) {
                    System.out.println(header[i] + ": " + row[i]);
                }
                System.out.println("---");
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}

