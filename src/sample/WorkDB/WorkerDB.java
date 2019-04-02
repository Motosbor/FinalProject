package sample.WorkDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Entitys.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

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

//    public static void loginCheck(String login, String password, Connection connection) throws SQLException{
//
//       String check = "SELECT EXIST(SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + " LIKE " + "? AND " +
//        Const.USER_PASSWORD + " LIKE ?)";
//
//       PreparedStatement statement = connection.prepareStatement(check);
//       statement.setString(1,login);
//       statement.setString(2,password);
//       statement.execute();
//
//    }


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
}
