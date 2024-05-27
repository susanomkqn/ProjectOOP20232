package org.group9.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    @FXML
    private TextField inputField;

    @FXML
    private Button runButton;

    @FXML
    private TextArea resultArea; // TextArea to display results

    private MemberController memberController;
    private ResultController resultController;
    private Stage stage; // Added Stage

    private static final Logger logger = Logger.getLogger(MainController.class.getName());

    @FXML
    public void initialize() {
        // Check if memberController is null
        if (memberController != null) {
            // Set action for the runButton
            runButton.setOnAction(e -> handleRunButtonAction());
        } else {
            logger.log(Level.WARNING, "MemberController is null. RunButton action not set.");
        }
    }

    private void handleRunButtonAction() {
        try {
            String input = inputField.getText();
            if (validateInput(input)) {
                String result = runQuery(input);
                if (resultController != null) {
                    resultController.setResult(result);
                    if (resultController.getStage() != null && !resultController.getStage().isShowing()) {
                        resultController.getStage().show();
                    }
                } else {
                    logger.log(Level.SEVERE, "ResultController is not set.");
                }
            } else {
                logger.log(Level.WARNING, "Invalid input: " + input);
                // Optionally show a warning dialog to the user
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error running query", ex);
        }
    }

    private boolean validateInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    private String runQuery(String query) {
        // Code to run the query
        return "Dummy result"; // Replace this with actual query execution logic
    }

    // Setter for the stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Getter for the runButton
    public Button getRunButton() {
        return runButton;
    }

    // Setter for the memberController
    public void setMemberController(MemberController memberController) {
        this.memberController = memberController;
        // Check if memberController is not null to avoid NullPointerException
        if (runButton != null) {
            // Set action for the runButton
            runButton.setOnAction(e -> handleRunButtonAction());
        }
    }

    // Setter for the resultController
    public void setResultController(ResultController resultController) {
        this.resultController = resultController;
    }

    // Trong MainController
    public Button getMemberButton() {
        return memberController.getMemberButton(); // Thay "getButton()" bằng phương thức thực tế trong MemberController để lấy nút từ MemberController
    }
}
