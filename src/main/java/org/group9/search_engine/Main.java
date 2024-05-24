package org.group9.search_engine;
import org.group9.news.News;
import org.group9.news.NewsReader;
import org.group9.search_engine.SearchEngine;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Bước 1: Đọc dữ liệu từ file CSV
        String csvFilePath = "NewsArticle.csv"; // Thay đổi đường dẫn đến file CSV của bạn
        List<News> newsList = NewsReader.readNewsFromCSV(csvFilePath);

        if (newsList == null) {
            System.out.println("Không thể đọc dữ liệu từ file CSV.");
            return;
        }

        // Bước 2: Tạo đối tượng SearchEngine và truyền danh sách các đối tượng News vào đó
        SearchEngine searchEngine = new SearchEngine(newsList);

        // Bước 3: Tìm kiếm và in kết quả
        String query = "blockchain"; // Truy vấn tìm kiếm của bạn
        searchEngine.searchAndPrintResults(query);
    }
}
