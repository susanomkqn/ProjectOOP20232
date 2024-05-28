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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.group9.news.News;
import org.group9.news.CSVReader;
import org.group9.search_engine.BasicSearchEngine;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> sortComboBox;

    @FXML
    private VBox resultsVBox; // Reference to VBox in result.fxml

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
            sortResults(searchResults); // Sort results based on selected criteria
            displayResults(searchResults); // Display sorted results
        } else {
            System.err.println("Error: Search engine is not initialized.");
        }
    }

    private void displayResults(List<News> results) {
        resultsVBox.getChildren().clear(); // Clear previous results
        if (results != null && !results.isEmpty()) {
            for (News news : results) {
                AnchorPane resultPane = createResultPane(news);
                resultsVBox.getChildren().add(resultPane);
            }
        } else {
            Label noResultsLabel = new Label("No results found.");
            noResultsLabel.setStyle("-fx-text-fill: black; -fx-font-size: 16;");
            resultsVBox.getChildren().add(noResultsLabel);
        }
    }

    private AnchorPane createResultPane(News news) {
        AnchorPane pane = new AnchorPane();
        pane.setStyle("-fx-background-radius: 20; -fx-background-color: #AFEEEE; -fx-border-radius: 15;");
        pane.setPrefHeight(150.0);
        pane.setPrefWidth(700.0);

        Label titleLabel = new Label("Title: " + news.getTitle());
        titleLabel.setLayoutX(14.0);
        titleLabel.setLayoutY(10.0);
        titleLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20;");

        Label dateLabel = new Label("Date: " + news.getDate());
        dateLabel.setLayoutX(14.0);
        dateLabel.setLayoutY(40.0);
        dateLabel.setStyle("-fx-text-fill: black;");

        Label authorLabel = new Label("Author: " + news.getAuthor());
        authorLabel.setLayoutX(14.0);
        authorLabel.setLayoutY(70.0);
        authorLabel.setStyle("-fx-text-fill: black;");

        Label urlLabel = new Label("URL: " + news.getUrl());
        urlLabel.setLayoutX(14.0);
        urlLabel.setLayoutY(100.0);
        urlLabel.setStyle("-fx-text-fill: black;");

        Label detailContentLabel = new Label("Detail content: " + truncateDetailContent(news.getDetailContents()));
        detailContentLabel.setLayoutX(14.0);
        detailContentLabel.setLayoutY(130.0);
        detailContentLabel.setStyle("-fx-text-fill: black;");

        pane.getChildren().addAll(titleLabel, dateLabel, authorLabel, urlLabel, detailContentLabel);

        return pane;
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

    private void sortResults(List<News> results) {
        String sortBy = sortComboBox.getValue();
        if ("Date".equals(sortBy)) {
            results.sort(Comparator.comparing(News::getDate).reversed());
        } else if ("Author".equals(sortBy)) {
            results.sort(Comparator.comparing(News::getAuthor));
        } else {
            // Default: Sort by Relevance (no additional sorting needed)
        }
    }
}
