package org.group9.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    protected void handleRunButtonAction(ActionEvent event) throws IOException {
        // Mở Result.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Result.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleMemberButtonAction(ActionEvent event) throws IOException {
        // Mở Member.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Member.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void handleExitButtonAction(ActionEvent event) {
        // Thoát ứng dụng
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}