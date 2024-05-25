package org.group9.news;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindAndSplitByName;

import java.util.Date;
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
    private String date; // Giữ nguyên dưới dạng String hoặc chuyển đổi thành Date

    @CsvBindByName(column = "TagName")
    private String tagname;

    @CsvBindByName(column = "Keyword")
    private String keyword; // Có thể cần xử lý trước khi gán vào đối tượng

    @CsvBindByName(column = "Detail Contents")
    private String detailContents; // Có thể cần xử lý trước khi gán vào đối tượng

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

    // Phương thức để chuyển đổi định dạng ngày tháng từ chuỗi sang Date hoặc LocalDateTime

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

    // Có thể cần xử lý trước khi gán vào thuộc tính keyword

    public void setKeyword(String keyword) {
        // Thực hiện xử lý keyword trước khi gán vào thuộc tính keyword
    }

    public String getDetailContents() {
        return detailContents;
    }

    // Có thể cần xử lý trước khi gán vào thuộc tính detailContents

    public void setDetailContents(String detailContents) {
        this.detailContents = detailContents;
    }

}
