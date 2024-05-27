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
    public List<News> searchAndPrintResults(String query) {
        List<String> queryTokens = tokenize(query);
        List<Double> scores = getBM25().getScores(tokenizedCorpus, queryTokens);
        List<Double> nonZeroScores = filterNonZeroScores(scores);
        return retrieveSearchResults(nonZeroScores);
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

    private List<News> retrieveSearchResults(List<Double> nonZeroScores) {
        List<News> searchResults = new ArrayList<>();
        int size = Math.min(getCorpus().size(), nonZeroScores.size()); // Sử dụng kích thước nhỏ hơn
        for (int i = 0; i < size; i++) {
            double score = nonZeroScores.get(i);
            if (score != 0.0) { // Only consider non-zero scores
                searchResults.add(getCorpus().get(i));
            }
        }
        return searchResults;
    }
}
