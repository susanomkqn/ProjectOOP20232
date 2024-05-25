package org.group9.search_engine;

import java.util.Scanner;
import org.group9.news.CSVReader; // Thêm import này

public class QueryRunner {

    public static void main(String[] args) {
        // Khởi tạo SearchEngine với dữ liệu từ file CSV
        SearchEngine searchEngine = new SearchEngine(CSVReader.readNewsFromCSV("NewsArticle.csv"));

        // Chuẩn bị corpus cho việc tìm kiếm
        searchEngine.prepareCorpus();

        // Nhập truy vấn từ người dùng và tìm kiếm
        runQuery(searchEngine);
    }

    public static void runQuery(SearchEngine searchEngine) {
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine().trim();

        // Tìm kiếm và in kết quả
        searchEngine.searchAndPrintResults(query);

        scanner.close();
    }
}
