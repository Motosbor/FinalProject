package sample.Controllers;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Entitys.*;
import sample.Helpers.AlertHelper;
import sample.Helpers.XMLWorker;
import sample.WorkDB.ConnectorToDB;
import sample.WorkDB.WorkerDB;
import sample.animation.Animate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;


public class HomeController1 implements Initializable {

    private String nameField;
    private String lastNameField;
    private String loginField;
    private String passwordField;
    private Animate animate;
    public static AlertHelper alertHelper;

    @FXML
    private TableView<Customer> CustomersTable;

    @FXML
    private TableColumn<Customer, Double> customerDiscountCellInCustomersTable;

    @FXML
    private TableView<?> NewSellTable;

    @FXML
    private TableColumn<Sell,String> customerNameCellAllSellsTable;

    @FXML
    private TextField productPriceFieldProductsTable;

    @FXML
    private TableColumn<Customer, String> customerNameCellInCustomersTable;

    @FXML
    private TextField customerNameFieldInNewSell;

    @FXML
    private TableColumn<Sell,String> sellerNameCellAllSellsTable;

    @FXML
    private TableColumn<?, ?> productBalanceCellProductsTable;

    @FXML
    private TableColumn<Sell, String> productNameCellAllSellsTable;

    @FXML
    private TableColumn<?, ?> productPriceCellInNewSell;

    @FXML
    private TableColumn<Sell, Integer> productCountCellAllSellsTable;

    @FXML
    private TextField productNameFieldInNewSell;

    @FXML
    private TextField productNameFieldProductsTable;

    @FXML
    private Button backButton;

    @FXML
    private TextField customerNameFieldInCustomersTable;

    @FXML
    private TableColumn<Sell,Double> totalPriceCellAllSellsTable;

    @FXML
    private TableColumn<?, ?> productCellInNewSell;

    @FXML
    private TableColumn<?, ?> productNameCellProductsTable;

    @FXML
    private TableView<?> AllSellsTable;

    @FXML
    private TextField nameOfSellerFieldAllSellsTable;

    @FXML
    private TextField productBalanceFieldProductsTable;

    @FXML
    private TableView<?> ProductsTable;

    @FXML
    private TableColumn<?, ?> productPriceCellProductsTable;

    @FXML
    private TableColumn<?, ?> productBalanceCellInNewSell;

    @FXML
    private ImageView inYan;

    @FXML
    private TextField productCountFieldInNewSell;

    @FXML
    private TextField ThisUser;

    @FXML
    private TableColumn<Sell, Date> dateCellAllSellsTable;



    @FXML
    void addSellButtonInNewSell(ActionEvent event) {

    }

    @FXML
    void allProductButtonInNewSell(ActionEvent event) {

    }

    @FXML
    void findProductButtonInNewSell(ActionEvent event) {

    }

    @FXML
    void addCustomerButtonInCustomersTable(ActionEvent event) {

    }

    @FXML
    void findCustomerButtonInCustomersTable(ActionEvent event) {

    }

    @FXML
    void deleteCustomerButtonInCustomersTable(ActionEvent event) {

    }

    @FXML
    void findSellerSalesButtonAllSellsTable(ActionEvent event) {

    }

    @FXML
    void findMySellsButtonAllSellsTable(ActionEvent event) {

    }

    @FXML
    void findAllSellsButtonAllSellsTable(ActionEvent event) {

    }

    @FXML
    void addProductButtonProductsTable(ActionEvent event) {

    }

    @FXML
    void deleteProductButtonProductsTable(ActionEvent event) {

    }

    @FXML
    void findProductButtonProductsTable(ActionEvent event) {

    }

    @FXML
    void changeProductButtonProductsTable(ActionEvent event) {

    }



    @Override

    public void initialize(URL location, ResourceBundle resources) {

        /**
         * Заполнение таблицы клиентов
         */
        customerNameCellInCustomersTable.setCellValueFactory(new PropertyValueFactory<Customer,String>("customerName"));
        customerDiscountCellInCustomersTable.setCellValueFactory(new PropertyValueFactory<Customer,Double>("discount"));
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(WorkerDB.takeAllCustomers(ConnectorToDB.getInstance().getConnection()));
        CustomersTable.setItems(customerObservableList);

        productNameCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell,String>("product"));
        productCountCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell,Integer>("count"));
        totalPriceCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell,Double>("totalPrice"));
        sellerNameCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell,String>("seller"));
        customerNameCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell,String>("customer"));
        dateCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell, Date>("dateOfSell"));
        ObservableList<Sell> sellObservableList = FXCollections.observableArrayList();



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

                User user;
                WorkerDB.signInUser(user = new User(nameField,lastNameField,loginField,passwordField),ConnectorToDB.getInstance().getConnection());
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Succes!","Пользователь добавлен!\n");
                TableVieW.getItems().add(user);

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