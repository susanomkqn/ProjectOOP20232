package org.group9.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
        VBox root = mainLoader.load();

        Parent memberRoot = FXMLLoader.load(getClass().getResource("Member.fxml"));
        Parent resultRoot = FXMLLoader.load(getClass().getResource("Result.fxml"));

        root.getChildren().setAll(memberRoot);

        Scene scene = new Scene(root, 868, 670);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Your Application Title");
        primaryStage.show();

        Stage memberStage = new Stage();
        memberStage.setScene(new Scene(memberRoot, 925, 667));
        memberStage.setTitle("Member View");
        memberStage.show();

        Stage resultStage = new Stage();
        resultStage.setScene(new Scene(resultRoot, 724, 543));
        resultStage.setTitle("Result View");
        resultStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
