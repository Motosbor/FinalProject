package sample.Controllers;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Entitys.AllUsers;
import sample.Entitys.User;
import sample.Helpers.AlertHelper;
import sample.Helpers.XMLWorker;
import sample.WorkDB.ConnectorToDB;
import sample.WorkDB.WorkerDB;
import sample.animation.Animate;
import javafx.scene.control.TextField;


public class HomeController implements Initializable {

    private String nameField;
    private String lastNameField;
    private String loginField;
    private String passwordField;

    private Animate animate;
    public static AlertHelper alertHelper;
    @FXML
    public ImageView inYan;
    @FXML
    private TableColumn<User, String> loginCell;

    @FXML
    private TableColumn<User, String> passwordCell;

    @FXML
    private TableColumn<User, String> nameCell;

    @FXML
    private TableColumn<User, String> lastNameCell;

    @FXML
    private TableView<User> TableVieW;

    @FXML
    private TextField name;

    @FXML
    private TextField lastName;

    @FXML
    private TextField password;

    @FXML
    private TextField login;


    @Override

    public void initialize(URL location, ResourceBundle resources) {

        nameCell.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        lastNameCell.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        loginCell.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        passwordCell.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        ObservableList<User> list = FXCollections.observableArrayList(WorkerDB.takeAllUsers(ConnectorToDB.getInstance().getConnection()));
        TableVieW.setItems(list);
        AllUsers allUsers = new AllUsers(list);
        XMLWorker.createXMLFile(allUsers);

        animate = new Animate(inYan);
        animate.playAnimation();


    }

    public void turnBack(ActionEvent actionEvent) {

        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("/sample/View/loginPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    void giveMeMyXML(ActionEvent event) {

        File file = new File("users1.xml");
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void addUser(ActionEvent actionEvent) {

         loginField = login.getText().trim();
         passwordField = password.getText().trim();
         nameField = name.getText().trim();
         lastNameField = lastName.getText().trim();

        if(!loginField.isEmpty() && !passwordField.isEmpty() && !nameField.isEmpty() && !lastNameField.isEmpty()){
            try {

                WorkerDB.signInUser(new User(nameField,lastNameField,loginField,passwordField),ConnectorToDB.getInstance().getConnection());
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Succes!","Пользователь добавлен!\n");
                ObservableList<User> list = FXCollections.observableArrayList(WorkerDB.takeAllUsers(ConnectorToDB.getInstance().getConnection()));
                TableVieW.setItems(list);

            } catch (SQLException  e) {
                alertHelper = new AlertHelper(Alert.AlertType.ERROR,"Error","Такой логин уже существует!");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.ERROR,"Error","Введите все данные для регистрации");
        }
    }


    public void deleteUser(ActionEvent actionEvent) {
    }
}