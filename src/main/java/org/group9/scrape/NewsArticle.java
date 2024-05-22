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

public class NewsArticle {
    public static void main(String[] args) {
        String baseUrl = "https://theconversation.com/us/topics/blockchain-11427";
        String absUrl = "https://theconversation.com";
        String csvFile = "NewsArticle.csv";

        try {
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
                String[] headerRecord = {"Title", "URL", "Type", "Date", "Author", "Detail Contents", "TagName", "KeyWord"};
                writer.writeNext(headerRecord);

                for (int page = 1; page <= 20; page++) {
                    String url = baseUrl + "?page=" + page;
                    Document doc = Jsoup.connect(url).get();

                    Elements articleHeaders = doc.select(".article--header");

                    for (Element articleHeader : articleHeaders) {
                        Element link = articleHeader.selectFirst("a[href]");
                        String Url = link.attr("href");
                        String Title = link.text();
                        String Type = "News Article";

                        String pageUrl = absUrl + Url;
                        Document document = Jsoup.connect(pageUrl).get();

                        Element publishedTime = document.selectFirst("time[itemprop=datePublished]");
                        String Date = publishedTime.text();

                        Elements authorNames = document.select("span.fn.author-name");
                        List<String> authors = new ArrayList<>();
                        for (Element authorName : authorNames) {
                            authors.add(authorName.text());
                        }

                        Element articleBody = document.selectFirst("div[itemprop=articleBody]");
                        String detailContents = articleBody.text();

                        Elements tag = document.select(".topic-list-item");
                        List<String> tags = new ArrayList<>();
                        for (Element tagName : tag) {
                            tags.add(tagName.text());
                        }

                        Elements contentDivs = document.select("div.content-body");
                        List<String> keyWords = new ArrayList<>();
                        for (Element div : contentDivs) {
                            Elements keyWordElements = div.select("a");
                            for (Element keyWord : keyWordElements) {
                                keyWords.add(keyWord.text());
                            }
                        }

                        String[] dataRecord = {Title, pageUrl, Type, Date, String.join(", ", authors), detailContents, String.join(", ", tags), String.join(", ", keyWords)};
                        writer.writeNext(dataRecord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
