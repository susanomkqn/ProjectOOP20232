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
        FXMLLoader helloviewLoader = new FXMLLoader(getClass().getResource("/helloview.fxml"));
        Parent helloviewRoot = helloviewLoader.load();
        Scene mainScene = new Scene(helloviewRoot, 868, 670);

        // Load Member.fxml
        FXMLLoader memberLoader = new FXMLLoader(getClass().getResource("/Member.fxml"));
        Parent memberRoot = memberLoader.load();
        Scene memberScene = new Scene(memberRoot, 925, 667);

        // Load Result.fxml
        FXMLLoader resultLoader = new FXMLLoader(getClass().getResource("/Result.fxml"));
        Parent resultRoot = resultLoader.load();
        Scene resultScene = new Scene(resultRoot, 724, 543);

        // Set the controller for each FXML file
        MainController mainController = helloviewLoader.getController();
        MemberController memberController = memberLoader.getController();
        ResultController resultController = resultLoader.getController();

        mainController.setMemberController(memberController);
        mainController.setResultController(resultController);

        // Pass stages to controllers if needed
        resultController.setStage(new Stage());
        memberController.setStage(new Stage());

        // Show the main stage
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Your Application Title");
        primaryStage.show();

        // Show the member stage
        Stage memberStage = new Stage();
        memberStage.setScene(memberScene);
        memberStage.setTitle("Member View");
        memberController.setStage(memberStage);
        memberStage.show();

        // Show the result stage
        Stage resultStage = resultController.getStage();
        resultStage.setScene(resultScene);
        resultStage.setTitle("Result View");
    }

    public static void main(String[] args) {
        launch(args);
    }
}