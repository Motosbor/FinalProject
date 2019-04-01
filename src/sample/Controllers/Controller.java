package sample.Controllers;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Helpers.Loader;
import sample.WorkDB.ConnectorToDB;
import sample.WorkDB.WorkerDB;

public class Controller implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registryButton;

    @FXML
    private Button enterButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField loginField;


    @FXML
    void initialize() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        enterButton.setOnAction(event -> {
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();
            if(!login.isEmpty() && !password.isEmpty()){
                try {
                    if(WorkerDB.loginCheck(login,password, ConnectorToDB.giveMeConnection())){
                        enterButton.getScene().getWindow().hide();
                        new Loader("/sample/View/app.fxml");
                    }else {
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Неверный логин или пароль");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Введите логин и пароль");
                alert.showAndWait();

            }
        });

//        registryButton.setOnAction(event -> {
//
//            registryButton.getScene().getWindow().hide();
//
//            new Loader("/sample/View/signUp.fxml");
//
//        });

    }

    private void loginUser(String login, String password) {
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void changeButt(javafx.event.ActionEvent actionEvent) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/sample/View/signUp.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}

