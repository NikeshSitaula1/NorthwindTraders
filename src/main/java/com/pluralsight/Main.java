package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try{
            // load the MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        // 1. open a connection to the database
            // use the database URL to point to the correct database
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    "root",
                    "yearup");
        // create statement
            // the statement is tied to the open connection
            Statement statement = connection.createStatement();
        // define your query
            String query = "SELECT * FROM products";

        // 2. Execute your query
            ResultSet results = statement.executeQuery(query);
            // process the results
            while (results.next()) {
                int productId = results.getInt("ProductID");
                String productName = results.getString("ProductName");
                System.out.println(productId + ". " + productName);
            }
        // 3. Close the connection
            connection.close();

        }catch (ClassNotFoundException e){
            System.out.println("There was an issue finding a clas: ");
            e.printStackTrace();
        }
        catch (SQLException e){
            System.out.println("There was an SQL issue: ");
            e.printStackTrace();
        }

    }
}