package org.group9.scrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Blog extends AbstractNewsScraper {

    @Override
    protected Elements getArticleHeaders(Document doc) {
        return doc.select(".entry-title");
    }

    @Override
    protected Element getLinkElement(Element articleHeader) {
        return articleHeader.selectFirst("a[href]");
    }

    @Override
    protected String getUrl(Element link) {
        return link.attr("href");
    }

    @Override
    protected String getTitle(Element link) {
        return link.text();
    }

    @Override
    protected Document getArticleDocument(String articleUrl) throws IOException {
        return Jsoup.connect(articleUrl).get();
    }

    @Override
    protected String getDate(Document articleDoc) {
        Elements datePublished = articleDoc.select("span[class = published]");
        return datePublished.text();
    }

    @Override
    protected List<String> getAuthors(Document articleDoc) {
        Elements authorNames = articleDoc.select("span[class = author-name]");
        List<String> authors = new ArrayList<>();
        for (Element authorName : authorNames) {
            authors.add(authorName.text());
        }
        return authors;
    }

    @Override
    protected String getDetailContents(Document articleDoc) {
        Elements detailContents = articleDoc.select("div[class = entry-content clear]");
        return detailContents.text();
    }

    @Override
    protected List<String> getTags(Document articleDoc) {
        Elements tagElements = articleDoc.select(".ast-terms-link");
        List<String> tags = new ArrayList<>();
        for (Element tagName : tagElements) {
            tags.add(tagName.text());
        }
        return tags;
    }

    @Override
    protected List<String> getKeyWords(Document articleDoc) {
        return new ArrayList<>(); // No keywords available for blogs in this example
    }

    @Override
    protected int getMaxPages() {
        return 7;
    }

    @Override
    protected String getPageSuffix(int page) {
        return "page/" + page;
    }

    @Override
    protected String getArticleType() {
        return "Blog Post";
    }
}
