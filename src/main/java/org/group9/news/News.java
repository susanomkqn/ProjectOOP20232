package org.group9.news;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindAndSplitByName;

import java.util.List;

public class News {
    @CsvBindByName(column = "Title")
    private String title;

    @CsvBindByName(column = "URL")
    private String url;

    @CsvBindByName(column = "Type")
    private String type;

    @CsvBindByName(column = "Author")
    private String author;

    @CsvBindByName(column = "Date")
    private String date;

    @CsvBindByName(column = "TagName")
    private String tagname;

    @CsvBindByName(column = "Keyword")
    private String keyword;

    @CsvBindByName(column = "Detail Contents")
    private String contents;

    private List<String> tokens;

    // Constructor
    public News() {
    }

    // Getter và Setter cho các thuộc tính

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
