package org.group9.scrape;

import java.io.IOException;

public class Run {
    public static void main(String[] args) {
        try {
            NewsScraper newsArticleScraper = new NewsArticle();
            newsArticleScraper.scrapeArticles("https://theconversation.com/us/topics/blockchain-11427", "NewsArticle.csv");

            NewsScraper newsArticle2Scraper = new NewsArticle2();
            newsArticle2Scraper.scrapeArticles("https://thefintechtimes.com/category/news/blockchain/", "NewsArticle2.csv");

            NewsScraper blogScraper = new Blog();
            blogScraper.scrapeArticles("https://blockchainshiksha.com/blog/", "Blog2.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
