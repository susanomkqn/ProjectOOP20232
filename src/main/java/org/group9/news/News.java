package org.group9.news;

import com.opencsv.bean.CsvBindByName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private String detailContents;

    private List<String> tokens;

    public News() {
    }

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

    public String getDetailContents() {
        return detailContents;
    }

    public void setDetailContents(String detailContents) {
        this.detailContents = detailContents;
    }

    public Date getParsedDate() {
        String[] dateFormats = {
                "MMMM d, yyyy h.mma z", // Format with time and time zone
                "MMMM d, yyyy"          // Format without time and time zone
        };

        for (String format : dateFormats) {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
            try {
                // Remove "Published: " prefix if present
                String dateStr = date.replace("Published: ", "").trim();
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                // Try the next format
            }
        }
        // If none of the formats work, return null or handle the error as appropriate
        return null;
    }
}