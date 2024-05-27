package org.group9.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.group9.search_engine.QueryRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
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
    private static final Logger logger = Logger.getLogger(MainController.class.getName());

    @FXML
    public void initialize() {
        runButton.setOnAction(e -> handleRunButtonAction());
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
        InputStream originalSystemIn = System.in;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setIn(new ByteArrayInputStream(query.getBytes(StandardCharsets.UTF_8)));
        System.setOut(new PrintStream(baos));

        try {
            QueryRunner.main(new String[0]);
            System.out.flush();
            return baos.toString();
        } finally {
            System.setIn(originalSystemIn);
            System.setOut(originalSystemOut);
        }
    }

    public void setMemberController(MemberController memberController) {
        this.memberController = memberController;
    }

    public void setResultController(ResultController resultController) {
        this.resultController = resultController;
    }
}
