package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        File file = new File("users.xml");
        Parent root = FXMLLoader.load(getClass().getResource("View/loginPage.fxml"));
        primaryStage.setTitle("Work_IT");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);

    }
}
