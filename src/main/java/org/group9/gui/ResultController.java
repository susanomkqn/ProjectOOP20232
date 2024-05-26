package org.group9.gui;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ResultController {
    @FXML
    private TextArea resultArea; // TextArea to display results

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setResult(String result) {
        resultArea.setText(result);
    }
}