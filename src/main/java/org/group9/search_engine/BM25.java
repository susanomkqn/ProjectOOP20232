package org.group9.search_engine;
import java.util.List;
import java.util.ArrayList;

public class BM25 {
    private static final double K1 = 1.2;
    private static final double B = 0.75;
    private static final double DELTA = 1.0;
    private final int corpusSize;  // Number of documents in corpus

    public BM25(int corpusSize) {
        this.corpusSize = corpusSize;
    }

    public List<Double> getScores(List<List<String>> tokenizedCorpus, List<String> query) {
        List<Double> scores = new ArrayList<>();

        for (int i = 0; i < corpusSize; i++) {
            scores.add(0.0);
        }
        for (int i = 0; i < corpusSize; i++) {
            double score = 0.0;
            for (String term : query) {
                double tf = calculateTermFrequency(tokenizedCorpus.get(i), term);
                double idf = calculateInverseDocumentFrequency(tokenizedCorpus, term);
                double tuso = tf * (K1 + 1);
                double mauso = tf + K1 * (1 - B + B * (tokenizedCorpus.get(i).size() / getAverageDocumentLength(tokenizedCorpus)));
                double bm25 = idf * tuso / mauso;
                score += bm25;
            }
            scores.set(i, score);
        }
        return scores;
    }

    private double calculateTermFrequency(List<String> document, String term) {
        int termFrequency = 0;
        for (String word : document) {
            if (word.equals(term)) {
                termFrequency++;
            }
        }
        return termFrequency;
    }


    private double calculateInverseDocumentFrequency(List<List<String>> tokenizedCorpus, String term) {
        int documentFrequency = 0;
        for (List<String> document : tokenizedCorpus) {
            if (document.contains(term)) {
                documentFrequency++;
            }
        }
        double idf = Math.log((corpusSize - documentFrequency + 0.5) / (documentFrequency + 0.5));
        return idf;
    }

    private double getAverageDocumentLength(List<List<String>> tokenizedCorpus) {
        int totalLength = 0;
        for (List<String> document : tokenizedCorpus) {
            totalLength += document.size();
        }
        return (double) totalLength / tokenizedCorpus.size();
    }
}



