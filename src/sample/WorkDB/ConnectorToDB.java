package sample.WorkDB;

import java.sql.*;
import java.sql.Connection;

public class ConnectorToDB {

    public static Connection giveMeConnection (){

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;

        try {

            connection = DriverManager.getConnection("jdbc:sqlite:WorkIT.db");
            Statement statement = connection.createStatement();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return connection;
    }
}
