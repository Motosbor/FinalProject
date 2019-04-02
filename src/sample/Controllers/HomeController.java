package sample.Controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import sample.Entitys.User;
import sample.Helpers.Loader;
import sample.WorkDB.ConnectorToDB;
import sample.WorkDB.WorkerDB;


public class HomeController implements Initializable {

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

    @Override

    public void initialize(URL location, ResourceBundle resources) {

        C1.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        C2.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        C3.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        C4.setCellValueFactory(new PropertyValueFactory<User, String>("password"));

        ObservableList<User> list = FXCollections.observableArrayList(WorkerDB.takeAllUsers(ConnectorToDB.getInstance().getConnection()));
        TableVieW.setItems(list);
    }

    public void turnBack(ActionEvent actionEvent) {

        Parent tableViewParent = null;
        try {
            tableViewParent = FXMLLoader.load(getClass().getResource("/sample/View/sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }


}