package sample.WorkDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Controllers.HomeController1;
import sample.Entitys.Customer;
import sample.Entitys.Product;
import sample.Entitys.Sell;
import sample.Entitys.User;

import java.sql.*;
import java.util.ArrayList;


public class WorkerDB {

    public static void signInUser(User user, Connection connection) throws SQLException{


            String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_LOGIN + "," + Const.USER_PASSWORD + ","
                    + Const.USER_NAME + "," + Const.USER_LASTNAME + ")" + "VALUES (?,?,?,?)";


                PreparedStatement statement = connection.prepareStatement(insert);
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.setString(4, user.getLastName());
                statement.execute();

    }


    public static boolean loginCheck(String login, String password, Connection connection) throws SQLException{

        boolean res = false;
        ArrayList<User> checkuser = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Const.USER_TABLE);
        while (resultSet.next()){

            User user = new User(resultSet.getInt(Const.SELLER_ID),resultSet.getString(Const.USER_NAME),resultSet.getString(Const.USER_LASTNAME),
                    resultSet.getString(Const.USER_LOGIN),resultSet.getString(Const.USER_PASSWORD));
            checkuser.add(user);

        }

        for (User u : checkuser) {
            if(u.getLogin().equals(login)&&u.getPassword().equals(password)){
                HomeController1.user = u;
                res = true;
                return res;
            }
        }

        return res;

    }

    public static ObservableList<User> takeAllUsers(Connection connection){
        ObservableList<User> allUsers = FXCollections.observableArrayList();

        Statement statement = null;
        try {

            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Const.USER_TABLE);
            while (resultSet.next()){
            User user = new User(resultSet.getInt(Const.SELLER_ID),resultSet.getString(Const.USER_NAME),resultSet.getString(Const.USER_LASTNAME),
                    resultSet.getString(Const.USER_LOGIN),resultSet.getString(Const.USER_PASSWORD));
            allUsers.add(user);
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return allUsers;
    }

    public static ObservableList<Customer> takeAllCustomers(Connection connection){
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Const.CUSTOMER_TABLE);
            while (resultSet.next()){
                Customer customer = new Customer(resultSet.getInt(Const.CUSTOMER_ID),resultSet.getString(Const.CUSTOMER_NAME),resultSet.getDouble(Const.CUSTOMER_DISCOUNT));
                allCustomers.add(customer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allCustomers;
    }

    public static ObservableList<Sell> takeAllSales(Connection connection){
        ObservableList<Sell> sellObservableList = FXCollections.observableArrayList();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Const.SELLS_TABLE);
            while (resultSet.next()){
                Sell sell = new Sell(resultSet.getInt(Const.SELLER_ID),resultSet.getString(Const.SELLER),resultSet.getString(Const.CUSTOMER),resultSet.getString(Const.PRODUCT_NAME),
                        resultSet.getInt(Const.PRODUCT_COUNT),resultSet.getDouble(Const.TOTAL_PRICE),resultSet.getDate(Const.DATE_OF_SELL));
                sellObservableList.add(sell);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  sellObservableList;
    }

    public static ObservableList<Product> takeAllProducts(Connection connection){
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + Const.PRODUCTS_TABLE);
            while (resultSet.next()){
                Product product = new Product(resultSet.getString(Const.PRODUCT_NAME),resultSet.getDouble(Const.PRODUCT_PRICE_INPR),resultSet.getInt(Const.PRODUCT_BALANCE));
                productObservableList.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productObservableList;
    }

    public static ObservableList<Product> takeOneProduct(String productName,Connection connection) throws SQLException{

        ObservableList<Product> productObservableList = FXCollections.observableArrayList();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + Const.PRODUCTS_TABLE + " WHERE " + Const.PRODUCT_NAME + " LIKE ?");
            statement.setString(1,productName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product(resultSet.getString(Const.PRODUCT_NAME),resultSet.getDouble(Const.PRODUCT_PRICE_INPR),resultSet.getInt(Const.PRODUCT_BALANCE));
                productObservableList.add(product);
            }

        return productObservableList;
    }
    public static void changeProduct(Product product,Connection connection) throws SQLException{

        String update = "UPDATE " + Const.PRODUCTS_TABLE + " SET " +  Const.PRODUCT_PRICE_INPR + "= ?," +
                 Const.PRODUCT_BALANCE + " = ? WHERE " + Const.PRODUCT_NAME + " LIKE ?";
        PreparedStatement statement = connection.prepareStatement(update);
        statement.setDouble(1,product.getPrice());
        statement.setInt(2,product.getBalance());
        statement.setString(3,product.getProductName());
        statement.execute();
    }


    public static void addProduct(Product product,Connection connection) throws SQLException{

        String insert = "INSERT INTO " + Const.PRODUCTS_TABLE + "(" + Const.PRODUCT_NAME + "," + Const.PRODUCT_PRICE_INPR + ","
                + Const.PRODUCT_BALANCE + ")VALUES (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1,product.getProductName());
        statement.setDouble(2,product.getPrice());
        statement.setInt(3,product.getBalance());
        statement.execute();
    }
    public static void deleteProduct(Product product,Connection connection)throws SQLException{
        String delete = "DELETE FROM " + Const.PRODUCTS_TABLE + " WHERE " + Const.PRODUCT_NAME + " LIKE ?";
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.setString(1,product.getProductName());
        statement.execute();
    }

    public static double addSell(Product product,User user, Customer customer,int productCount,Connection connection) throws SQLException {

        java.util.Date javaDate = new java.util.Date();
        long getDate = javaDate.getTime();
        Date date = new Date(getDate);
        String insert = "INSERT INTO " + Const.SELLS_TABLE + "(" + Const.PRODUCT_NAME + "," + Const.PRODUCT_COUNT + "," + Const.TOTAL_PRICE + "," +
                Const.SELLER + "," + Const.CUSTOMER + "," + Const.DATE_OF_SELL + "," + Const.SELLER_ID + ")" + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1,product.getProductName());
        statement.setInt(2,productCount);
        Customer temp = getCustomerByID(customer.getId(),connection);
        double tempPrice = product.getPrice() * productCount;
        if(temp.equals(null)){
            statement.setDouble(3,tempPrice);
            statement.setString(4,user.getName());
            statement.setString(5,customer.getCustomerName());
            statement.setDate(6,date);
            statement.setInt(7,user.getId());
            statement.execute();
            return tempPrice;
        }else {
            statement.setDouble(3,(tempPrice)-((tempPrice*temp.getDiscount())/100));
            statement.setString(4,user.getName());
            statement.setString(5,customer.getCustomerName());
            statement.setDate(6,date);
            statement.setInt(7,user.getId());
            statement.execute();
            return (tempPrice)-((tempPrice*temp.getDiscount())/100);
        }
    }

    public static void addCustomer(String customerName,double discount,Connection connection) throws SQLException {
        String insert = "INSERT INTO " + Const.CUSTOMER_TABLE + "(" + Const.CUSTOMER_NAME + "," + Const.CUSTOMER_DISCOUNT + ")" + "VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1,customerName);
        statement.setDouble(2,discount);
        statement.execute();
    }

    public static Customer getCustomerByID(int customerId,Connection connection)throws SQLException{
        Customer customerFromDB = null;

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + Const.CUSTOMER_TABLE + " WHERE " + Const.CUSTOMER_ID + " LIKE ?");
        statement.setInt(1,customerId);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            customerFromDB = new Customer(resultSet.getInt(Const.CUSTOMER_ID),resultSet.getString(Const.CUSTOMER_NAME),resultSet.getDouble(Const.CUSTOMER_DISCOUNT));
        }
        return customerFromDB;
    }
    public static ObservableList<Customer> getCustomerByName(String customerName,Connection connection) throws SQLException{
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + Const.CUSTOMER_TABLE + " WHERE " + Const.CUSTOMER_NAME + " LIKE ?");
        statement.setString(1,customerName);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Customer customer = new Customer(resultSet.getInt(Const.CUSTOMER_ID),resultSet.getString(Const.CUSTOMER_NAME),resultSet.getDouble(Const.CUSTOMER_DISCOUNT));
            observableList.add(customer);
        }
        return observableList;
    }
    public static void deleteCustomer(Customer customer,Connection connection) throws SQLException{
        String delete = "DELETE FROM " + Const.CUSTOMER_TABLE + " WHERE " + Const.CUSTOMER_ID + " LIKE ?";
        PreparedStatement statement = connection.prepareStatement(delete);
        statement.setInt(1,customer.getId());
        statement.execute();
    }

    public static ObservableList<Sell> getAllSellsBySeller(User user,Connection connection)throws SQLException{
        ObservableList<Sell> observableList = FXCollections.observableArrayList();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + Const.SELLS_TABLE + " WHERE " + Const.SELLER_ID + " LIKE ?");
        statement.setInt(1,user.getId());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            Sell sell = new Sell(resultSet.getInt(Const.SELLER_ID),resultSet.getString(Const.SELLER),resultSet.getString(Const.CUSTOMER),resultSet.getString(Const.PRODUCT_NAME),
                    resultSet.getInt(Const.PRODUCT_COUNT),resultSet.getDouble(Const.TOTAL_PRICE),resultSet.getDate(Const.DATE_OF_SELL));
            observableList.add(sell);
        }
        return observableList;
    }

}
