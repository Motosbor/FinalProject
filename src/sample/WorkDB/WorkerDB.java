package sample.WorkDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Entitys.Customer;
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

            User user = new User(resultSet.getString(Const.USER_NAME),resultSet.getString(Const.USER_LASTNAME),
                    resultSet.getString(Const.USER_LOGIN),resultSet.getString(Const.USER_PASSWORD));
            checkuser.add(user);

        }

        for (User u : checkuser) {
            if(u.getLogin().equals(login)&&u.getPassword().equals(password)){
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
            User user = new User(resultSet.getString(Const.USER_NAME),resultSet.getString(Const.USER_LASTNAME),
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
                Customer customer = new Customer(resultSet.getString(Const.CUSTOMER_NAME),resultSet.getDouble(Const.CUSTOMER_DISCOUNT));
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
                Sell sell = new Sell(resultSet.getString(Const.SELLER),resultSet.getString(Const.CUSTOMER),resultSet.getString(Const.PRODUCT_NAME),
                        resultSet.getInt(Const.))
            }
        }
        return  sellObservableList;
    }
}
