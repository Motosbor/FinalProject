package sample.Controllers;

import java.beans.EventHandler;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Helpers.Loader;
import sample.WorkDB.ConnectorToDB;
import sample.WorkDB.WorkerDB;

public class Controller {

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
                //Alerts alerts = new Alerts(Alert.AlertType.INFORMATION,"Капец", EventHandler<enterButton>sh)
            }
        });

        registryButton.setOnAction(event -> {

            registryButton.getScene().getWindow().hide();

            new Loader("/sample/View/signUp.fxml");

        });

    }

    private void loginUser(String login, String password) {
    }
}

