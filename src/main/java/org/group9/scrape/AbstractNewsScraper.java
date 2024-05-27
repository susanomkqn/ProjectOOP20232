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

public abstract class AbstractNewsScraper implements NewsScraper {

    protected abstract Elements getArticleHeaders(Document doc);
    protected abstract Element getLinkElement(Element articleHeader);
    protected abstract String getUrl(Element link);
    protected abstract String getTitle(Element link);
    protected abstract Document getArticleDocument(String articleUrl) throws IOException;
    protected abstract String getDate(Document articleDoc);
    protected abstract List<String> getAuthors(Document articleDoc);
    protected abstract String getDetailContents(Document articleDoc);
    protected abstract List<String> getTags(Document articleDoc);
    protected abstract List<String> getKeyWords(Document articleDoc);

    @Override
    public void scrapeArticles(String baseUrl, String csvFile) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            String[] headerRecord = {"Title", "URL", "Type", "Date", "Author", "Detail Contents", "TagName", "KeyWord"};
            writer.writeNext(headerRecord);

            for (int page = 1; page <= getMaxPages(); page++) {
                String url = baseUrl + getPageSuffix(page);
                Document doc = Jsoup.connect(url).get();
                Elements articleHeaders = getArticleHeaders(doc);

                for (Element articleHeader : articleHeaders) {
                    Element link = getLinkElement(articleHeader);
                    String articleUrl = getUrl(link);
                    String title = getTitle(link);
                    Document articleDoc = getArticleDocument(articleUrl);

                    String date = getDate(articleDoc);
                    List<String> authors = getAuthors(articleDoc);
                    String detailContents = getDetailContents(articleDoc);
                    List<String> tags = getTags(articleDoc);
                    List<String> keyWords = getKeyWords(articleDoc);

                    String[] dataRecord = {title, articleUrl, getArticleType(), date, String.join(", ", authors), detailContents, String.join(", ", tags), String.join(", ", keyWords)};
                    writer.writeNext(dataRecord);
                }
            }
        }
    }

    protected abstract int getMaxPages();
    protected abstract String getPageSuffix(int page);
    protected abstract String getArticleType();
}