package sample.Controllers;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Helpers.Loader;
import sample.WorkDB.ConnectorToDB;
import sample.WorkDB.WorkerDB;

public class signUpController {

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


    @FXML
    void initialize() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        registryButton.setOnAction(event -> {
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();
            String name = nameField.getText().trim();
            String lastName = lastNameField.getText().trim();

            if(!login.isEmpty() && !password.isEmpty() && !name.isEmpty() && !lastName.isEmpty()){

                try {

                    WorkerDB.signInUser(login, password, name, lastName, ConnectorToDB.giveMeConnection());

                    registryButton.getScene().getWindow().hide();
                    alert.setTitle("Succes");
                    alert.setHeaderText(null);
                    alert.setContentText("Успешная регистрация!\n Логин - " + login + "\n" + "Пароль - " + password);
                    alert.showAndWait();
                    new Loader("/sample/View/sample.fxml");

                }catch (SQLException e){

                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Такой логин уже существует!");
                    alert.showAndWait();

                }

            }else {

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Введите все данные для регистрации");
                alert.showAndWait();

            }

        });

    }
}

