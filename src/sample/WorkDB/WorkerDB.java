package sample.WorkDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WorkerDB {

    public static void signInUser(String login,  String password, String name, String lastname,  Connection connection) throws SQLException{


            String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_LOGIN + "," + Const.USER_PASSWORD + ","
                    + Const.USER_NAME + "," + Const.USER_LASTNAME + ")" + "VALUES (?,?,?,?)";


                PreparedStatement statement = connection.prepareStatement(insert);
                statement.setString(1, login);
                statement.setString(2, password);
                statement.setString(3, name);
                statement.setString(4, lastname);
                statement.execute();

    }

    public static void loginCheck(String login, String password, Connection connection) throws SQLException{

       String check = "SELECT EXIST(SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + " LIKE " + "? AND " +
        Const.USER_PASSWORD + " LIKE ?)";

       PreparedStatement statement = connection.prepareStatement(check);
       statement.setString(1,login);
       statement.setString(2,password);
       statement.execute();

    }

}
