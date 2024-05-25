package org.group9.search_engine;

import org.group9.news.News;

public class NewsScorePair {
    private final News news;
    private final double score;

    public NewsScorePair(News news, double score) {
        this.news = news;
        this.score = score;
    }

    public News getNews() {
        return news;
    }

    public double getScore() {
        return score;
    }
}
