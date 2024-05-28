package org.group9.search_engine;

import org.group9.news.News;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class BasicSearchEngine extends SearchEngine {
    private List<List<String>> tokenizedCorpus;

    public BasicSearchEngine(List<News> corpus) {
        super(corpus);
    }

    @Override
    protected List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        String[] words = text.toLowerCase().split("\\s+");
        tokens.addAll(Arrays.asList(words));
        return tokens;
    }

    @Override
    public void prepareCorpus() {
        tokenizedCorpus = new ArrayList<>();
        for (News news : getCorpus()) {
            tokenizedCorpus.add(tokenize(news.getTitle() + " " + news.getDetailContents()));
        }
    }

    @Override
    public List<News> searchAndPrintResults(String query) {
        List<String> queryTokens = tokenize(query);
        List<Double> scores = getBM25().getScores(tokenizedCorpus, queryTokens);

        List<NewsScorePair> newsScorePairs = new ArrayList<>();
        for (int i = 0; i < getCorpus().size(); i++) {
            News news = getCorpus().get(i);
            double score = scores.get(i);
            if (score != 0.0) {
                newsScorePairs.add(new NewsScorePair(news, score));
            }
        }

        newsScorePairs.sort((pair1, pair2) -> Double.compare(pair2.getScore(), pair1.getScore()));

        List<News> searchResults = new ArrayList<>();
        for (NewsScorePair pair : newsScorePairs) {
            searchResults.add(pair.getNews());
        }

        return searchResults;
    }

    public List<News> searchByAuthor(String authorQuery) {
        List<News> searchResults = new ArrayList<>();
        String lowerCaseQuery = authorQuery.toLowerCase(Locale.ROOT);
        for (News news : getCorpus()) {
            if (news.getAuthor().toLowerCase(Locale.ROOT).contains(lowerCaseQuery)) {
                searchResults.add(news);
            }
        }
        return searchResults;
    }

    public List<News> sortByDateDescending() {
        List<News> sortedNews = new ArrayList<>(getCorpus());
        sortedNews.sort(Comparator.comparing(News::getParsedDate).reversed());
        return sortedNews;
    }

    private static class NewsScorePair {
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
}