package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import sample.Entitys.User;
import sample.Helpers.Loader;


public class HomeController {

    @FXML
    private TableColumn<User, String> C3;

    @FXML
    private TableColumn<User, String> C4;

    @FXML
    private Button backButt;

    @FXML
    private ImageView imageButton;

    @FXML
    private TableColumn<User, String> C1;

    @FXML
    private TableView<User> TableVieW;

    @FXML
    private TableColumn<User, String> C2;

    @FXML
    void initialize() {

        backButt.setOnAction(event -> {
            backButt.getScene().getWindow().hide();
            new Loader("/sample/View/sample.fxml");
        });

        C1.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        C2.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        C3.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        C4.setCellValueFactory(new PropertyValueFactory<User, String>("password"));


        User user = new User("Kill","Pill","456","345");
        ObservableList<User> list = FXCollections.observableArrayList();
        list.add(user);
        TableVieW.setItems(list);
    }
}