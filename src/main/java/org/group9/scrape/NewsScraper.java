package org.group9.scrape;

import java.io.IOException;

public interface NewsScraper {
    void scrapeArticles(String baseUrl, String csvFile) throws IOException;
}
