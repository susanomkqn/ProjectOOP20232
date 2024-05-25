package org.group9.search_engine;

import org.group9.news.News;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchEngine {
    private final List<News> corpus;
    private final BM25 bm25;

    public SearchEngine(List<News> corpus) {
        this.corpus = corpus;
        this.bm25 = new BM25(corpus.size());
    }

    public void prepareCorpus() {
        tokenizeCorpus();
    }

    private void tokenizeCorpus() {
        for (News news : corpus) {
            List<String> tokens = tokenize(news.getTitle() + " " + news.getDetailContents());
            news.setTokens(tokens);
        }
    }

    private List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        String[] words = text.toLowerCase().split("\\s+");
        tokens.addAll(Arrays.asList(words));
        return tokens;
    }

    public void searchAndPrintResults(String query) {
        List<String> queryTokens = tokenize(query);
        List<List<String>> tokenizedCorpus = getTokenizedCorpus();

        // Get BM25 scores
        List<Double> scores = bm25.getScores(tokenizedCorpus, queryTokens);

        // Filter out zero scores
        List<Double> nonZeroScores = new ArrayList<>();
        for (Double score : scores) {
            if (score != 0.0) {
                nonZeroScores.add(score);
            }
        }

        // Print search results if there are non-zero scores, otherwise print no results message
        if (!nonZeroScores.isEmpty()) {
            printSearchResults(scores);
        } else {
            System.out.println("No results found.");
        }
    }

    private List<List<String>> getTokenizedCorpus() {
        List<List<String>> tokenizedCorpus = new ArrayList<>();
        for (News news : corpus) {
            tokenizedCorpus.add(news.getTokens());
        }
        return tokenizedCorpus;
    }

    public void printSearchResults(List<Double> scores) {
        // Create list of NewsScorePair
        List<NewsScorePair> newsScorePairs = new ArrayList<>();
        for (int i = 0; i < corpus.size(); i++) {
            News news = corpus.get(i);
            double score = scores.get(i);
            newsScorePairs.add(new NewsScorePair(news, score));
        }

        // Sort list by score in descending order
        newsScorePairs.sort((pair1, pair2) -> Double.compare(pair2.getScore(), pair1.getScore()));

        // Print results
        for (NewsScorePair pair : newsScorePairs) {
            News news = pair.getNews();
            double score = pair.getScore();
            if (score != 0.0) { // Only print if score is non-zero
                System.out.println("Title: " + news.getTitle());
                System.out.println("URL: " + news.getUrl());
                System.out.println("Detail Content: " + truncateDetailContent(news.getDetailContents()));
                System.out.println("Date: " + news.getDate());
                System.out.println("Author: " + news.getAuthor());
                System.out.println("Score: " + score);
                System.out.println();
            }
        }
    }

    private String truncateDetailContent(String detailContent) {
        int maxLength = 100; // Maximum length to print
        if (detailContent.length() <= maxLength) {
            return detailContent;
        } else {
            return detailContent.substring(0, maxLength) + "...";
        }
    }
}
