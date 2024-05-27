package org.group9.search_engine;

import org.group9.news.News;
import java.util.List;

public abstract class SearchEngine {
    private final List<News> corpus;
    private final BM25 bm25;

    public SearchEngine(List<News> corpus) {
        this.corpus = corpus;
        this.bm25 = new BM25(corpus.size());
    }

    public abstract void prepareCorpus();

    protected abstract List<String> tokenize(String text);

    public abstract List<News> searchAndPrintResults(String query); // Thay đổi kiểu trả về

    protected List<News> getCorpus() {
        return corpus;
    }

    protected BM25 getBM25() {
        return bm25;
    }
}
