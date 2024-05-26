package org.group9.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load Main.fxml
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent mainRoot = mainLoader.load();
        Scene mainScene = new Scene(mainRoot, 868, 670);

        // Load Member.fxml
        FXMLLoader memberLoader = new FXMLLoader(getClass().getResource("Member.fxml"));
        Parent memberRoot = memberLoader.load();
        Scene memberScene = new Scene(memberRoot, 925, 667);

        // Load Result.fxml
        FXMLLoader resultLoader = new FXMLLoader(getClass().getResource("Result.fxml"));
        Parent resultRoot = resultLoader.load();
        Scene resultScene = new Scene(resultRoot, 724, 543);

        // Set the controller for each FXML file
        MemberController memberController = memberLoader.getController();
        ResultController resultController = resultLoader.getController();

        // Show the main stage
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Your Application Title");
        primaryStage.show();

        // Show the member stage
        Stage memberStage = new Stage();
        memberStage.setScene(memberScene);
        memberStage.setTitle("Member View");
        memberStage.show();

        // Show the result stage
        Stage resultStage = new Stage();
        resultStage.setScene(resultScene);
        resultStage.setTitle("Result View");
        resultStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
