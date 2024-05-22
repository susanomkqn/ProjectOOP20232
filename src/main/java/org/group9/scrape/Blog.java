package org.group9.scrape;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class Blog {

    public static void main(String[] args) {
        String baseUrl = "https://aws.amazon.com/blogs/database/category/blockchain";
        String csvFile = "Blog.csv";

        try {
            // Create a CSV writer
            CSVWriter writer = new CSVWriter(new FileWriter(csvFile));

            // Write header to the CSV file
            String[] header = {"Title", "URL", "Type", "Date", "Author", "DetailContents", "TagName", "KeyWord"};
            writer.writeNext(header);

            for (int page = 2; page <= 8; page++) {
                String url = baseUrl + "/page/" + page;
                Document doc = Jsoup.connect(url).get();

                Elements articleHeaders = doc.select(".blog-post-title");

                for (Element articleHeader : articleHeaders) {
                    Element link = articleHeader.selectFirst("a[href]");
                    String Url = link.attr("href");
                    String Title = link.text();
                    String Type = "Blog Post";

                    Document document = Jsoup.connect(Url).get();

                    Elements datePublished = document.select("time[property=datePublished]");
                    String date = datePublished.text();

                    Elements authorNames = document.select("span[property=author]");
                    StringBuilder authors = new StringBuilder();
                    for (Element authorName : authorNames) {
                        String author = authorName.text();
                        authors.append(author).append(", ");
                    }

                    Elements detailContents = document.select("section[property=articleBody]");
                    String detailContent = detailContents.text();

                    String tagName = "NULL";

                    StringBuilder keyWords = new StringBuilder();
                    Elements keywords = detailContents.select("a");
                    for (Element key : keywords) {
                        String keyWord = key.text();
                        keyWords.append(keyWord).append(", ");
                    }

                    // Write data to CSV file
                    String[] data = {Title, Url, Type, date, authors.toString(), detailContent, tagName, keyWords.toString()};
                    writer.writeNext(data);
                }
            }

            // Close the CSV writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
