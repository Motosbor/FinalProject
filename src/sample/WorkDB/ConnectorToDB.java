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

            String createUserTable =
                    "CREATE TABLE User ( Id       " +
                            "INTEGER PRIMARY KEY AUTOINCREMENT, Login   " +
                            " VARCHAR NOT NULL UNIQUE ON CONFLICT ABORT, " +
                            "Password VARCHAR NOT NULL, Name     " +
                            "VARCHAR NOT NULL, LastName VARCHAR);";
            PreparedStatement preparedStatement = connection.prepareStatement(createUserTable);
            preparedStatement.execute();
            String createProductTable = "CREATE TABLE Product (" +
                    "    ID_Product     INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    ProductName    VARCHAR NOT NULL" +
                    "                           UNIQUE," +
                    "    ProductPrice   REAL    NOT NULL," +
                    "    ProductBalance INTEGER NOT NULL);";
            preparedStatement = connection.prepareStatement(createProductTable);
            preparedStatement.execute();
            String createSellsTable = "CREATE TABLE Sell (" +
                    "    ID_Sell      INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    ProductName  VARCHAR NOT NULL," +
                    "    ProductCount INTEGER NOT NULL," +
                    "    TotalPrice   REAL    NOT NULL," +
                    "    Seller       VARCHAR," +
                    "    Customer     VARCHAR," +
                    "    DateOfSale   DATE," +
                    "    Id           INTEGER);";
            preparedStatement = connection.prepareStatement(createSellsTable);
            preparedStatement.execute();
            String createCustomerTable = "CREATE TABLE Customer (" +
                    "    ID_Customer   INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    Customer_Name VARCHAR NOT NULL," +
                    "    Discount      REAL);";
            preparedStatement = connection.prepareStatement(createCustomerTable);
            preparedStatement.execute();



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
