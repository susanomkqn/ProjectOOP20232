package org.group9.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load Main.fxml (HelloView)
        FXMLLoader helloviewLoader = new FXMLLoader(getClass().getResource("/helloview.fxml"));
        Parent helloviewRoot = helloviewLoader.load();
        Scene mainScene = new Scene(helloviewRoot, 868, 670);

        // Load Member.fxml
        FXMLLoader memberLoader = new FXMLLoader(getClass().getResource("/Member.fxml"));
        Parent memberRoot = memberLoader.load();
        MemberController memberController = memberLoader.getController();
        Scene memberScene = new Scene(memberRoot, 925, 667);

        // Load Result.fxml
        FXMLLoader resultLoader = new FXMLLoader(getClass().getResource("/Result.fxml"));
        Parent resultRoot = resultLoader.load();
        ResultController resultController = resultLoader.getController();
        Scene resultScene = new Scene(resultRoot, 724, 543);

        // Set controllers and stages
        MainController mainController = helloviewLoader.getController();
        mainController.setMemberController(memberController);
        mainController.setResultController(resultController);
        mainController.setStage(primaryStage);

        memberController.setStage(new Stage());
        resultController.setStage(new Stage());

        // Set up main scene
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Your Application Title");

        // Show main stage
        primaryStage.show();

        // Event handling for switching to Member view
        mainController.getMemberButton().setOnAction(event -> {
            Stage memberStage = memberController.getStage();
            memberStage.setScene(memberScene);
            memberStage.setTitle("Member View");
            memberStage.show();
        });

        // Event handling for switching to Result view
        mainController.getRunButton().setOnAction(event -> {
            Stage resultStage = resultController.getStage();
            resultStage.setScene(resultScene);
            resultStage.setTitle("Result View");
            resultStage.show();
            // Perform any necessary actions in ResultController
            resultController.performQueryAndDisplayResults();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
