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
import sample.Helpers.AlertHelper;
import sample.Helpers.Loader;
import sample.WorkDB.ConnectorToDB;
import sample.WorkDB.WorkerDB;

public class Controller implements Initializable {


    public static String login;
    public static String password;
    public static AlertHelper alertHelper;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openSignUpForm(javafx.event.ActionEvent actionEvent) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/sample/View/signUp.fxml"));

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void openEnterUpForm(javafx.event.ActionEvent actionEvent) throws IOException {

        login = loginField.getText().trim();
        password = passwordField.getText().trim();

        if (!login.isEmpty() && !password.isEmpty()) {
            try {
                if (WorkerDB.loginCheck(login, password, ConnectorToDB.getInstance().getConnection())) {

                    Parent tableViewParent = FXMLLoader.load(getClass().getResource("/sample/View/app.fxml"));
                    Scene tableViewScene = new Scene(tableViewParent);
                    Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(tableViewScene);
                    window.show();

                } else {
                    alertHelper = new AlertHelper(Alert.AlertType.ERROR,"Login|Password Err","Неверный логин или пароль");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.ERROR,"Login|Password Err","Введите логин и пароль");
        }
    }
}

