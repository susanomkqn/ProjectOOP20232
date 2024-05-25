package org.group9.news;

import com.opencsv.bean.CsvToBeanBuilder;
import org.group9.news.News;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVReader {

    public static List<News> readNewsFromCSV() {
        String csvFilePath = "Database.csv";
        try (FileReader fileReader = new FileReader(csvFilePath)) {
            // Đọc dữ liệu từ file CSV và tạo danh sách tin tức
            List<News> corpus = new CsvToBeanBuilder<News>(fileReader)
                    .withType(News.class)
                    .build()
                    .parse();

            return corpus;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
