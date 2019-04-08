package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import sample.Entitys.XMLCollector;
import sample.Helpers.AlertHelper;
import sample.Helpers.XMLWorker;
import sample.WorkDB.ConnectorToDB;
import sample.WorkDB.WorkerDB;
import sample.animation.Animate;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class HomeController1 implements Initializable {

    private String nameField;
    private String lastNameField;
    private String loginField;
    private String passwordField;
    private Animate animate;
    public static AlertHelper alertHelper;
    public static User user;

    @FXML
    private TableView<Customer> CustomersTable;
    @FXML
    private TableColumn<Customer, Double> customerDiscountCellInCustomersTable;
    @FXML
    private TableColumn<Customer, String> customerNameCellInCustomersTable;
    @FXML
    private TableColumn<Customer,Integer> customerIDCellInCustomersTable1;
    @FXML
    private TextField customerNameFieldInCustomersTable;
    @FXML
    private TextField customerDiscountFieldInCustomersTable;

    @FXML
    private TableView<Product> NewSellTable;
    @FXML
    private TableColumn<Product, Double> productPriceCellInNewSell;
    @FXML
    private TextField productNameFieldInNewSell;
    @FXML
    private TextField customerIDFieldInNewSell;
    @FXML
    private TableColumn<Product, String> productCellInNewSell;
    @FXML
    private TableColumn<Product, Integer> productBalanceCellInNewSell;
    @FXML
    private TextField productCountFieldInNewSell;


    @FXML
    private TableView<Sell> AllSellsTable;
    @FXML
    private TableColumn<Sell,String> sellerNameCellAllSellsTable;
    @FXML
    private TableColumn<Sell, String> productNameCellAllSellsTable;
    @FXML
    private TableColumn<Sell, Integer> productCountCellAllSellsTable;
    @FXML
    private TableColumn<Sell,Double> totalPriceCellAllSellsTable;
    @FXML
    private TableColumn<Sell, Date> dateCellAllSellsTable;
    @FXML
    private TableColumn<Sell,String> customerNameCellAllSellsTable;
    @FXML
    private TableColumn<Sell,Integer> IDCellAllSellsTable1;
    @FXML
    private TextField nameOfSellerFieldAllSellsTable;
    @FXML
    private TextField idOfSellerFieldAllSellsTable1;


    @FXML
    private TableView<Product> ProductsTable;
    @FXML
    private TextField productPriceFieldProductsTable;
    @FXML
    private TableColumn<Product,Integer> productBalanceCellProductsTable;
    @FXML
    private TextField productNameFieldProductsTable;
    @FXML
    private TableColumn<Product,String> productNameCellProductsTable;
    @FXML
    private TextField productBalanceFieldProductsTable;
    @FXML
    private TableColumn<Product,Double> productPriceCellProductsTable;

    @FXML
    private TextField ThisUser;
    @FXML
    private ImageView inYan;
    @FXML
    private Button backButton;



    @FXML
    void addSellButtonInNewSell(ActionEvent event) {
        Product product = NewSellTable.getSelectionModel().getSelectedItem();
        if(product==null){
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Choose","Выберите товар из списка");
        }else if(!customerIDFieldInNewSell.getText().isEmpty()&&!productCountFieldInNewSell.getText().isEmpty()) {
            try {
                Integer.parseInt(customerIDFieldInNewSell.getText());
                Customer customer = WorkerDB.getCustomerByID(Integer.parseInt(customerIDFieldInNewSell.getText()), ConnectorToDB.getInstance().getConnection());
                if (customer!=null) {

                    try {
                        int productCount = Integer.parseInt(productCountFieldInNewSell.getText());
                        if(productCount>0){
                        product.setBalance(product.getBalance() - productCount);
                        if(product.getBalance()>=0) {
                            double totalPrice = WorkerDB.addSell(product, user, customer, productCount, ConnectorToDB.getInstance().getConnection());
                            fillAllSellsTable(false);
                            WorkerDB.changeProduct(product, ConnectorToDB.getInstance().getConnection());
                            fillProductsTable(false);
                            fillNewSellTable(false);
                            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION, "Succes", "Продажа добавлена! \n Покупатель - " + customer.getCustomerName() +
                                    "\n Продавец - " + user.getName() + " " + user.getLastName() + " \n Товар - " + product.getProductName() + " кол-во - " + productCount + " сумма покупки - " + totalPrice);
                        }else {
                            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"BalnceLessZero","Недопустимое кол-во товара");
                        }
                        }else {
                        alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"BalnceLessZero","Недопустимое кол-во товара");
                        }
                    }catch (NumberFormatException e){
                        alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Error","Недопустимый формат числа");
                    }
                }else {
                    alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"NotFound","Покупатель с таким ID не найден");
                }
            } catch (SQLException |NullPointerException ex) {
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION, "NotFound", "Не найден покупатель");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"MissInfo","Введите ID покупателя и кол-во товара");
        }
    }

    @FXML
    void allProductButtonInNewSell(ActionEvent event) {
        fillNewSellTable(false);
    }

    @FXML
    void findProductButtonInNewSell(ActionEvent event) {
        String productName = productNameFieldInNewSell.getText();
        if(!productName.isEmpty()){
            try {
                ObservableList<Product> oneProduct = FXCollections.observableArrayList(WorkerDB.takeOneProduct(productName, ConnectorToDB.getInstance().getConnection()));
                NewSellTable.setItems(oneProduct);
            }catch (SQLException e){
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Missmatch","Товар не найден");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Empty","Введите название товара");
        }

    }

    @FXML
    void addCustomerButtonInCustomersTable(ActionEvent event) {
        try {
            if (!customerNameFieldInCustomersTable.getText().isEmpty() && !customerDiscountFieldInCustomersTable.getText().isEmpty()) {
                String customerName = customerNameFieldInCustomersTable.getText();
                double discount = Double.parseDouble(customerDiscountFieldInCustomersTable.getText());
                WorkerDB.addCustomer(customerName, discount, ConnectorToDB.getInstance().getConnection());
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION, "Succes", "Покупатель " + customerName + " Добавлен" + " его персональная скидка - " + discount);
                fillCustomersTable(false);
            } else if (customerDiscountFieldInCustomersTable.getText().isEmpty()) {
                String customerName = customerNameFieldInCustomersTable.getText();
                WorkerDB.addCustomer(customerName, 0, ConnectorToDB.getInstance().getConnection());
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION, "Succes", "Покупатель " + customerName + " Добавлен" + " без персональной скидки");
                fillCustomersTable(false);
            } else {
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION, "MissingInfo", "Введите имя покупателя");
            }
        }catch (SQLException e){
            alertHelper = new AlertHelper(Alert.AlertType.ERROR,"Error","Что-то пошло не так");
        }

    }

    @FXML
    void findCustomerButtonInCustomersTable(ActionEvent event) {
        String customerName = customerNameFieldInCustomersTable.getText();
        if(!customerName.isEmpty()){
            try {
                ObservableList<Customer> observableList = WorkerDB.getCustomerByName(customerName, ConnectorToDB.getInstance().getConnection());
                if(!observableList.isEmpty()) {
                    CustomersTable.setItems(observableList);
                }else {
                    alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"NoSuchCustomer","Нет совпадений");
                }
            }catch (SQLException e){
                alertHelper = new AlertHelper(Alert.AlertType.ERROR,"SqlException","Ошибка БД");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"EmptyField","Введите имя покупателя");
        }
    }
    @FXML
    void AllCustomerButtonInCustomersTable(ActionEvent event){
        fillCustomersTable(false);
    }

    @FXML
    void deleteCustomerButtonInCustomersTable(ActionEvent event) {
        Customer customer = CustomersTable.getSelectionModel().getSelectedItem();
        if(customer!=null) {
            try {
                WorkerDB.deleteCustomer(customer, ConnectorToDB.getInstance().getConnection());
                fillCustomersTable(false);
            } catch (SQLException e) {
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION, "Choose", "Выберите покупателя для удаления");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION, "Choose", "Выберите покупателя для удаления");
        }
    }

    @FXML
    void findSellerSalesButtonAllSellsTable(ActionEvent event) {
        if(!idOfSellerFieldAllSellsTable1.getText().isEmpty()) {
            try {
                ObservableList<Sell> observableList = WorkerDB.getAllSellsBySeller(new User(Integer.parseInt(idOfSellerFieldAllSellsTable1.getText())), ConnectorToDB.getInstance().getConnection());
                AllSellsTable.setItems(observableList);
            }catch (SQLException e){
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Empty","Введите ID продавца");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Empty","Введите ID продавца");
        }
    }

    @FXML
    void findMySellsButtonAllSellsTable(ActionEvent event) {
        try {
            ObservableList<Sell> observableList = WorkerDB.getAllSellsBySeller(user, ConnectorToDB.getInstance().getConnection());
            AllSellsTable.setItems(observableList);
        }catch (SQLException e){
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Wrong","Что-то пошло не так");
        }
    }

    @FXML
    void findAllSellsButtonAllSellsTable(ActionEvent event) {
        fillAllSellsTable(false);
    }

    @FXML
    void addProductButtonProductsTable(ActionEvent event) {
       try {
           String productName = productNameFieldProductsTable.getText();
           Double productPrice = Double.parseDouble(productPriceFieldProductsTable.getText());
           Integer productBalance = Integer.parseInt(productBalanceFieldProductsTable.getText());
           Product product = new Product(productName,productPrice,productBalance);
           try {
               WorkerDB.addProduct(product, ConnectorToDB.getInstance().getConnection());
               fillProductsTable(false);
           }catch (SQLException e){
               alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"SQLerr","Недопустимы повторяющиеся имена товаров");
           }
       }catch (NumberFormatException e){
           alertHelper = new AlertHelper(Alert.AlertType.ERROR,"WrongInput","Введены недопустимые значения");
       }
    }

    @FXML
    void deleteProductButtonProductsTable(ActionEvent event) {
        Product product = ProductsTable.getSelectionModel().getSelectedItem();
        if (product!=null) {
            try {
                WorkerDB.deleteProduct(product, ConnectorToDB.getInstance().getConnection());
                fillProductsTable(false);
            } catch (SQLException e) {
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION, "Empty", "Выберите удаляемый товар");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Empty", "Выберите удаляемый товар");
        }
    }

    @FXML
    void findProductButtonProductsTable(ActionEvent event) {

        String productName = productNameFieldProductsTable.getText();
        if(!productName.isEmpty()){
            try {
                ObservableList<Product> oneProduct = FXCollections.observableArrayList(WorkerDB.takeOneProduct(productName, ConnectorToDB.getInstance().getConnection()));
                if (oneProduct.isEmpty()){
                    alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Missmatch","Товар не найден");
                }else {
                    ProductsTable.setItems(oneProduct);
                }
            }catch (SQLException e){
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"SQLerr","Что-то пошло не так");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Empty","Введите название товара");
        }

    }
    @FXML
    void allProductButtonInProductsTable(ActionEvent event){
        fillProductsTable(false);
    }
    @FXML
    void changeProductButtonProductsTable(ActionEvent event) {
        Product product = ProductsTable.getSelectionModel().getSelectedItem();

        if (product!=null) {
            try {
                if(!productPriceFieldProductsTable.getText().isEmpty())
                    try {
                    product.setPrice(Double.parseDouble(productPriceFieldProductsTable.getText()));
                    }catch (NumberFormatException e){
                    alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"WrongNumber","Неверный ввод значений цены");
                    }
                if(!productBalanceFieldProductsTable.getText().isEmpty()){
                    try {
                        product.setBalance(Integer.parseInt(productBalanceFieldProductsTable.getText()));
                    }catch (NumberFormatException e){
                        alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"WrongNumber","Неверный ввод значений остатка");
                    }
                }
                WorkerDB.changeProduct(product,ConnectorToDB.getInstance().getConnection());
                fillProductsTable(false);
                fillNewSellTable(false);
            } catch (SQLException e) {
                alertHelper = new AlertHelper(Alert.AlertType.INFORMATION, "SQLerr", "Что-то пошло не так");
            }
        }else {
            alertHelper = new AlertHelper(Alert.AlertType.INFORMATION,"Empty", "Выберите изменяемый товар");
        }

    }



    @Override

    public void initialize(URL location, ResourceBundle resources) {


        ThisUser.appendText(user.getName() + " " + user.getLastName() + " Ваш ID - " + user.getId());

        fillCustomersTable(true);
        fillAllSellsTable(true);
        fillProductsTable(true);
        fillNewSellTable(true);



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

        File file = new File("users.xml");
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void fillCustomersTable(boolean choise){
        if(choise) {
            customerNameCellInCustomersTable.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
            customerDiscountCellInCustomersTable.setCellValueFactory(new PropertyValueFactory<Customer, Double>("discount"));
            customerIDCellInCustomersTable1.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        }
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(WorkerDB.takeAllCustomers(ConnectorToDB.getInstance().getConnection()));
        CustomersTable.setItems(customerObservableList);
    }
    public void fillAllSellsTable(boolean choise){
        if(choise) {
            productNameCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell, String>("product"));
            productCountCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell, Integer>("count"));
            totalPriceCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell, Double>("totalPrice"));
            sellerNameCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell, String>("seller"));
            IDCellAllSellsTable1.setCellValueFactory(new PropertyValueFactory<Sell,Integer>("sellerId"));
            customerNameCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell, String>("customer"));
            dateCellAllSellsTable.setCellValueFactory(new PropertyValueFactory<Sell, Date>("dateOfSell"));
        }
        ObservableList<Sell> sellObservableList = FXCollections.observableArrayList(WorkerDB.takeAllSales(ConnectorToDB.getInstance().getConnection()));
        AllSellsTable.setItems(sellObservableList);
    }
    public void fillProductsTable(boolean choise){
        if(choise) {
            productNameCellProductsTable.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
            productPriceCellProductsTable.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
            productBalanceCellProductsTable.setCellValueFactory(new PropertyValueFactory<Product, Integer>("balance"));
        }
        ObservableList<Product> productObservableList = FXCollections.observableArrayList(WorkerDB.takeAllProducts(ConnectorToDB.getInstance().getConnection()));
        ProductsTable.setItems(productObservableList);
    }
    public void fillNewSellTable(boolean choise){
        if(choise) {
            productCellInNewSell.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
            productPriceCellInNewSell.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
            productBalanceCellInNewSell.setCellValueFactory(new PropertyValueFactory<Product, Integer>("balance"));
        }
        ObservableList<Product> productObservableList = FXCollections.observableArrayList(WorkerDB.takeAllProducts(ConnectorToDB.getInstance().getConnection()));
        NewSellTable.setItems(productObservableList);
    }


    public void SellsTAB(Event event) {

        ArrayList<Object> list = new ArrayList<>(AllSellsTable.getItems());
        XMLCollector xmlCollector = new XMLCollector(list);
        XMLWorker.createXMLFile(xmlCollector, "view.xsl");

    }

    public void CustomersTAB(Event event) {
        ArrayList<Object> list = new ArrayList<>(CustomersTable.getItems());

        XMLCollector XMLCollector = new XMLCollector(list);

        XMLWorker.createXMLFile(XMLCollector,"view.xsl");
    }


    public void ProductsTAB(Event event) {
        ArrayList<Object> list = new ArrayList<>(ProductsTable.getItems());
        XMLCollector xmlCollector = new XMLCollector(list);
        XMLWorker.createXMLFile(xmlCollector, "view.xsl");
    }

    public void NewSellTAB(Event event) {
    }
}