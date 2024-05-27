package org.group9.scrape;

import org.group9.scrape.AbstractNewsScraper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsArticle2 extends AbstractNewsScraper {

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
        Element publishedTime = articleDoc.selectFirst("time.entry-date.published");
        return publishedTime.text();
    }

    @Override
    protected List<String> getAuthors(Document articleDoc) {
        Elements authorNames = articleDoc.select("span.author.vcard");
        List<String> authors = new ArrayList<>();
        for (Element authorName : authorNames) {
            authors.add(authorName.text());
        }
        return authors;
    }

    @Override
    protected String getDetailContents(Document articleDoc) {
        Element articleBody = articleDoc.selectFirst("div.penci-entry-content.entry-content");
        return articleBody.text();
    }

    @Override
    protected List<String> getTags(Document articleDoc) {
        Elements tagElements = articleDoc.select(".penci-cat-links");
        List<String> tags = new ArrayList<>();
        for (Element tagName : tagElements) {
            tags.add(tagName.text());
        }
        return tags;
    }

    @Override
    protected List<String> getKeyWords(Document articleDoc) {
        Elements contentDivs = articleDoc.select("div.penci-entry-content.entry-content");
        List<String> keyWords = new ArrayList<>();
        for (Element div : contentDivs) {
            Elements keyWordElements = div.select("a");
            for (Element keyWord : keyWordElements) {
                keyWords.add(keyWord.text());
            }
        }
        return keyWords;
    }

    @Override
    protected int getMaxPages() {
        return 107;
    }

    @Override
    protected String getPageSuffix(int page) {
        return "page/" + page;
    }

    @Override
    protected String getArticleType() {
        return "News Article";
    }
}
