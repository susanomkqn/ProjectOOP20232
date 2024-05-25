package org.group9.search_engine;

import org.group9.news.News;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void searchAndPrintResults(String query) {
        List<String> queryTokens = tokenize(query);
        List<Double> scores = getBM25().getScores(tokenizedCorpus, queryTokens);
        List<Double> nonZeroScores = filterNonZeroScores(scores);

        if (!nonZeroScores.isEmpty()) {
            printSearchResults(scores);
        } else {
            System.out.println("No results found.");
        }
    }

    private List<Double> filterNonZeroScores(List<Double> scores) {
        List<Double> nonZeroScores = new ArrayList<>();
        for (Double score : scores) {
            if (score != 0.0) {
                nonZeroScores.add(score);
            }
        }
        return nonZeroScores;
    }

    private void printSearchResults(List<Double> scores) {
        // Create list of NewsScorePair
        List<NewsScorePair> newsScorePairs = new ArrayList<>();
        for (int i = 0; i < getCorpus().size(); i++) {
            News news = getCorpus().get(i);
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
