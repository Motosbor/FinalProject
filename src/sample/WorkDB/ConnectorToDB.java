package sample.WorkDB;

import java.sql.*;
import java.sql.Connection;

public class ConnectorToDB  {

    private static ConnectorToDB instance;
    private static Connection connection;

    private ConnectorToDB (){
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

       this.connection = connection;
    }

    public static ConnectorToDB getInstance (){

        if (instance == null){
            instance = new ConnectorToDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
