package sample.Controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Entitys.User;
import sample.Helpers.AlertHelper;
import sample.Helpers.Loader;
import sample.WorkDB.ConnectorToDB;
import sample.WorkDB.WorkerDB;

public class signUpController implements Initializable {


    public static AlertHelper alertHelper;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registryButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField loginField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void isnertRegistryData(javafx.event.ActionEvent actionEvent) throws IOException {

        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();
        String name = nameField.getText().trim();
        String lastName = lastNameField.getText().trim();

        if(!login.isEmpty() && !password.isEmpty() && !name.isEmpty() && !lastName.isEmpty()){
            try {

                WorkerDB.signInUser(new User(name,lastName,login,password),ConnectorToDB.getInstance().getConnection());
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Succes!","Успешная регистрация!\nваш логин:" + login +"\nваш пароль:" + password);
                Parent tableViewParent = FXMLLoader.load(getClass().getResource("/sample/View/sample.fxml"));
                Scene tableViewScene = new Scene(tableViewParent);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();

            } catch (SQLException e) {
                alertHelper = new AlertHelper(Alert.AlertType.ERROR,"Error","Такой логин уже существует!");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.ERROR,"Error","Введите все данные для регистрации");
        }
    }
}

