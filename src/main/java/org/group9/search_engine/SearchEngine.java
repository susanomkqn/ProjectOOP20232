package org.group9.search_engine;

import java.util.*;
import org.group9.news.News;
import org.group9.search_engine.BM25; // Thêm dòng này


public class SearchEngine {
    private BM25 search;
    private List<News> corpus;

    public SearchEngine(List<News> corpus) {
        this.corpus = corpus;
        this.search = new BM25(corpus.size());
    }

    // Tokenize và tính toán BM25 cho corpus
    public void prepareCorpus() {
        tokenizeCorpus();
    }

    // Tokenize corpus
    private void tokenizeCorpus() {
        for (News news : corpus) {
            List<String> tokens = tokenize(news.getTitle() + " " + news.getContents());
            news.setTokens(tokens);
        }
    }

    // Tokenize một chuỗi văn bản
    private List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        String[] words = text.toLowerCase().split("\\s+");
        tokens.addAll(Arrays.asList(words));
        return tokens;
    }

    // Tìm kiếm và in kết quả
    public void searchAndPrintResults(String query) {
        List<Double> scores = search(query);
        printSearchResults(scores);
    }

    // Tìm kiếm
    public List<Double> search(String query) {
        List<String> queryTokens = tokenize(query);
        List<Double> scores = search.getScores(getTokenizedCorpus(), queryTokens);
        return scores;
    }

    // Lấy danh sách các tokenized corpus
    private List<List<String>> getTokenizedCorpus() {
        List<List<String>> tokenizedCorpus = new ArrayList<>();
        for (News news : corpus) {
            tokenizedCorpus.add(news.getTokens());
        }
        return tokenizedCorpus;
    }

    // In kết quả tìm kiếm
    public void printSearchResults(List<Double> scores) {
        List<News> sortedResults = new ArrayList<>(corpus);
        sortedResults.sort((news1, news2) -> {
            double score1 = scores.get(corpus.indexOf(news1));
            double score2 = scores.get(corpus.indexOf(news2));
            return Double.compare(score2, score1);
        });

        for (News news : sortedResults) {
            double score = scores.get(corpus.indexOf(news));
            System.out.println("Title: " + news.getTitle());
            System.out.println("URL: " + news.getUrl());
            System.out.println("Score: " + score);
            System.out.println();
        }
    }
}
