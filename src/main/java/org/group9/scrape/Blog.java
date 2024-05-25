package org.group9.scrape;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Blog {

    public static void main(String[] args) {
        String baseUrl = "https://blockchainshiksha.com/blog/";
        String csvFile = "Blog.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            // Write header to the CSV file
            String[] header = {"Title", "URL", "Type", "Date", "Author", "DetailContents", "TagName", "KeyWord"};
            writer.writeNext(header);

            for (int page = 1; page <= 7; page++) {
                String url = baseUrl + "/page/" + page;
                Document doc = Jsoup.connect(url).get();

                Elements articleHeaders = doc.select(".entry-title");

                for (Element articleHeader : articleHeaders) {
                    Element link = articleHeader.selectFirst("a[href]");
                    String Url = link.attr("href");
                    String Title = link.text();
                    String Type = "Blog Post";

                    Document document = Jsoup.connect(Url).get();

                    Elements datePublished = document.select("span[class = published]");
                    String date = datePublished.text();

                    Elements authorNames = document.select("span[class = author-name]");
                    StringBuilder authors = new StringBuilder();
                    for (Element authorName : authorNames) {
                        String author = authorName.text();
                        authors.append(author).append(", ");
                    }

                    Elements detailContents = document.select("div[class = entry-content clear]");
                    String detailContent = detailContents.text();

                    Elements tag = document.select(".ast-terms-link");
                    List<String> tags = new ArrayList<>();
                    for (Element tagName : tag) {
                        tags.add(tagName.text());
                    }

                    String keyWords = "NULL";

                    // Write data to CSV file
                    String[] data = {Title, Url, Type, date, authors.toString(), detailContent, tags.toString(), keyWords.toString()};
                    writer.writeNext(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}