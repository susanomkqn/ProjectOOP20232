package org.group9.search_engine;

public class BM25 {
    private static final double k1=1.2;
    private static final double b=0.75;
    private static final double delta=1.0;
    public BM25(){
        this(1.2,0.75,1.0);
    }

    public BM25(double k1, double b, double delta) {
    }
}
