package org.group9.news;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class NewsReader {

    public static List<News> readNewsFromCSV(String csvFilePath) {
        try {
            return new CsvToBeanBuilder<News>(new FileReader(csvFilePath))
                    .withType(News.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
