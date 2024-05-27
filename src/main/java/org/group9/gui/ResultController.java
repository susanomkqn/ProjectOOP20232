package org.group9.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.group9.news.News;
import org.group9.news.CSVReader;
import org.group9.search_engine.SearchEngine;
import org.group9.search_engine.BasicSearchEngine;
import java.util.List;

import java.io.IOException;

public class ResultController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> sortComboBox;

    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label urlLabel;

    @FXML
    private Label detailContentLabel;

    private SearchEngine searchEngine;

    private List<News> corpus;

    @FXML
    public void initialize() {
        corpus = CSVReader.readNewsFromCSV("Database.csv");
        if (corpus != null) {
            searchEngine = new BasicSearchEngine(corpus);
            searchEngine.prepareCorpus();
            sortComboBox.getItems().addAll("Relevance", "Date", "Author");
            sortComboBox.setValue("Relevance");
        } else {
            System.err.println("Error: Corpus could not be loaded.");
        }
    }

    @FXML
    private void handleSearchButtonAction() {
        String query = searchField.getText().trim();
        List<News> searchResults = searchEngine.searchAndPrintResults(query);
        if (!searchResults.isEmpty()) {
            // Hiển thị kết quả đầu tiên
            displayResult(searchResults.get(0));
        } else {
            // Xử lý khi không tìm thấy kết quả
            titleLabel.setText("Không tìm thấy kết quả.");
            dateLabel.setText("");
            authorLabel.setText("");
            urlLabel.setText("");
            detailContentLabel.setText("");
        }
    }

    @FXML
    public void displayResult(News news) {
        titleLabel.setText("Title: " + news.getTitle());
        dateLabel.setText("Date: " + news.getDate());
        authorLabel.setText("Author: " + news.getAuthor());
        urlLabel.setText("Url: " + news.getUrl());
        detailContentLabel.setText("Detail content: " + truncateDetailContent(news.getDetailContents()));
    }




    private String truncateDetailContent(String detailContent) {
        int maxLength = 100; // Maximum length to print
        if (detailContent.length() <= maxLength) {
            return detailContent;
        } else {
            return detailContent.substring(0, maxLength) + "...";
        }
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) throws IOException {
        // Quay trở lại Main.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/helloview.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}