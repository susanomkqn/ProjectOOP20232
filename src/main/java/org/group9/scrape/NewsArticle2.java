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

public class NewsArticle2 {
    public static void main(String[] args) {
        String baseUrl = "https://thefintechtimes.com/category/news/blockchain/";
        String csvFile = "NewsArticle2.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            String[] headerRecord = {"Title", "URL", "Type", "Date", "Author", "Detail Contents", "TagName", "KeyWord"};
            writer.writeNext(headerRecord);

            for (int page = 1; page <= 107; page++) {
                String url = baseUrl + "page/" + page;
                Document doc = Jsoup.connect(url).get();

                Elements articleHeaders = doc.select(".entry-title");

                for (Element articleHeader : articleHeaders) {
                    Element link = articleHeader.selectFirst("a[href]");
                    String Url = link.attr("href");
                    String Title = link.text();
                    String Type = "News Article";

                    Document document = Jsoup.connect(Url).get();

                    Element publishedTime = document.selectFirst("time.entry-date.published");
                    String Date = publishedTime.text();

                    Elements authorNames = document.select("span.author.vcard");
                    List<String> authors = new ArrayList<>();
                    for (Element authorName : authorNames) {
                        authors.add(authorName.text());
                    }

                    Element articleBody = document.selectFirst("div.penci-entry-content.entry-content");
                    String detailContents = articleBody.text();

                    Elements tag = document.select(".penci-cat-links");
                    List<String> tags = new ArrayList<>();
                    for (Element tagName : tag) {
                        tags.add(tagName.text());
                    }

                    Elements contentDivs = document.select("div.penci-entry-content.entry-content");
                    List<String> keyWords = new ArrayList<>();
                    for (Element div : contentDivs) {
                        Elements keyWordElements = div.select("a");
                        for (Element keyWord : keyWordElements) {
                            keyWords.add(keyWord.text());
                        }
                    }

                    String[] dataRecord = {Title, Url, Type, Date, String.join(", ", authors), detailContents, String.join(", ", tags), String.join(", ", keyWords)};
                    writer.writeNext(dataRecord);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}