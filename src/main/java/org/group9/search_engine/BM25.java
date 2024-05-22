package org.group9.search_engine;
import java.util.List;
import java.util.ArrayList;

public class BM25 {
    private static final double K1=1.2;
    private static final double B=0.75;
    private static final double DELTA=1.0;
    private final int  corpusSize;  //number of documents in corpus
    public BM25(int corpusSize) {
        this.corpusSize=corpusSize;
    }
    public List<Double>  getScores (List<List<String>> tokenizedCorpus, List<String> query) {
        List<Double> scores = new ArrayList<>();

        for (int i = 0; i < corpusSize; i++) {
            scores.add(0.0);
        }
        for (int i=0;i<corpusSize; i++) {
            double score = 0.0;
            for (String term : query) {
                double tf = calculateTermFrequency(tokenizedCorpus.get(i), term);
                double idf = calculateInverseDocumentFrequency(tokenizedCorpus, term);
                double tuso = tf * (K1 + 1);
                double mauso = tf + K1 * (1 - B + B * (tokenizedCorpus.get(i).size() / getAverageDocumentLength(tokenizedCorpus)));
                double bm25 = idf * tuso / mauso;
                score += bm25;
            }
            scores.add(score);
        }
        return scores;


    }

    }
