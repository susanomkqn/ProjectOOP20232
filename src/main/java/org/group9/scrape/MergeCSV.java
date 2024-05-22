package org.group9.scrape;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeCSV {

    public static void main(String[] args) {
        List<String> fileNames = new ArrayList<>();
        fileNames.add("NewsArticle.csv");
        fileNames.add("Blog.csv");
        // Add more file names as needed

        List<String[]> allLines = new ArrayList<>();

        for (String fileName : fileNames) {
            try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
                allLines.addAll(reader.readAll());
            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter("DataBase.csv"))) {
            writer.writeAll(allLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
