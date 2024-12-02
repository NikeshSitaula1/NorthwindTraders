package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println(
                    "Application needs two arguments to run: " +
                            "java com.pluralsight.NorthwindTraders <username> <password>");
            System.exit(1);
        }
        // get the username and password from the command line args
        String username = args[0];
        String password = args[1];

        try{
            // load the MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        // 1. open a connection to the database
            // use the database URL to point to the correct database
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/northwind",
                    username,
                    password);


        // 2. Execute your query
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM products;");

            ResultSet results = pStatement.executeQuery();
            // process the results
            while (results.next()) {
                int col1 = results.getInt("productID");
                String col2 = results.getString("productName");
                double col3 = results.getDouble("UnitPrice");
                int col4 = results.getInt("UnitsInStock");

                System.out.println(col1 + ". " + col2 + " | " + col3 + " | " + col4 );

            }
        // 3. Close the connection
            results.close();
            pStatement.close();
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