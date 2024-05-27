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
import org.group9.search_engine.BasicSearchEngine;

import java.io.IOException;
import java.util.List;

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

    private BasicSearchEngine searchEngine;

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
    private void handleSearchButtonAction(ActionEvent event) {
        String query = searchField.getText().trim();
        if (searchEngine != null) {
            List<News> searchResults = searchEngine.searchAndPrintResults(query); // Sử dụng phương thức search
            displayFirstResult(searchResults);
        } else {
            System.err.println("Error: Search engine is not initialized.");
        }
    }

    private void displayFirstResult(List<News> results) {
        if (results != null && !results.isEmpty()) {
            News firstResult = results.get(0);
            titleLabel.setText("Title: " + firstResult.getTitle());
            dateLabel.setText("Date: " + firstResult.getDate());
            authorLabel.setText("Author: " + firstResult.getAuthor());
            urlLabel.setText("URL: " + firstResult.getUrl());
            detailContentLabel.setText("Detail content: " + truncateDetailContent(firstResult.getDetailContents()));
        } else {
            titleLabel.setText("No results found.");
            dateLabel.setText("");
            authorLabel.setText("");
            urlLabel.setText("");
            detailContentLabel.setText("");
        }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/helloview.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
