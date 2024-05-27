package org.group9.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ResultController {

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
    @FXML
    private Button backButton;

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setResult(String result) {
        // Parse and display result data in the respective labels
        titleLabel.setText("Title: Example Title");
        dateLabel.setText("Date: 2024-05-27");
        authorLabel.setText("Author: Example Author");
        urlLabel.setText("Url: http://example.com");
        detailContentLabel.setText("Detail content: " + result);
    }

    @FXML
    private void handleBackButtonAction() {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    private void handleSearchButtonAction() {
        // Add your search functionality here
    }
}
