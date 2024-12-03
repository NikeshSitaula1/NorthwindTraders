package com.pluralsight;

import java.sql.*;

public class Main {
    public static void main(String[] args)  {

        try{
            // load the MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        if (args.length != 3) {
            System.out.println(
                    "Application needs three arguments to run: " +
                            "java com.pluralsight.NorthwindTraders <username> <password> <sqlServerAddress>");
            System.exit(1);
        }
        // get the username and password from the command line args
        String username = args[0];
        String password = args[1];
        String sqlServerAddress = args[2];

        String options = """
         =================================================
                      ✈️Northwind Traders™️
         =================================================
            Please select from the following choices:
            1 - Display all products
            2 - Display all customers
            3 - Display all categories
            0 - Exit
         =================================================
         >>\s""";

        int selection;

        do {
            try {
                selection = Console.PromptForInt(options);
                if (selection == 1 ){
                    try {
                        doDatabaseProducts(username, password, sqlServerAddress);
                    } catch (SQLException e) {

                        e.printStackTrace();
                    }
                }
                else if (selection == 2) {
                    try {
                        doDatabaseCustomers(username, password, sqlServerAddress);
                    } catch (SQLException e) {

                        e.printStackTrace();
                    }
                }
                else if (selection == 3) {
                    try {
                        doDatabaseCategories(username, password, sqlServerAddress);
                    } catch (SQLException e) {

                        e.printStackTrace();
                    }
                }
                else if (selection == 0) {
                    return;
                }
                else {
                    System.out.println("Invalid Entry. Please try again."); //If input is different from the options
                }
            } catch (Exception e) {
                System.out.println("Invalid entry. Please try again." + e.getMessage());
            }
        } while (true);
    }

    public static void doDatabaseProducts(String username, String password, String sqlServerAddress) throws SQLException{

        try
                (
                        Connection connection = DriverManager.getConnection(sqlServerAddress, username, password);
                        PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM products;");
                )
        {

            try(ResultSet results = pStatement.executeQuery();)
            {

                System.out.println("Product ID |  Product Name |  Unit Price    | UnitsInStock ");
                System.out.println("--------------------------------------------------------------------------------------------------------");
                // process the results
                while (results.next()) {
                    int col1 = results.getInt("productID");
                    String col2 = results.getString("productName");
                    double col3 = results.getDouble("UnitPrice");
                    int col4 = results.getInt("UnitsInStock");

                    System.out.println(col1 + ". " + col2 + " | " + col3 + " | " + col4 );

                }
            }
        } catch (SQLException e){
            System.out.println("There was an SQL issue: ");
            e.printStackTrace();
        }
    }

    public static void doDatabaseCustomers(String username, String password, String sqlServerAddress) throws SQLException{

        try (
                Connection connection = DriverManager.getConnection(sqlServerAddress, username, password);
                PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM customers;");
            )
        {
            try (ResultSet results = pStatement.executeQuery();)
            {
                System.out.println("Customer Name |  Company Name |  City    | Country |  Phone | ");
                System.out.println("--------------------------------------------------------------------------------------------------------");

                while (results.next()) {
                    String col1 = results.getString("contactName");
                    String col2 = results.getString("companyName");
                    String col3 = results.getString("City");
                    String col4 = results.getString("Country");
                    String col5 = results.getString("Phone");

                    System.out.println(col1 + "   | " + col2 + "   | " + col3 + "  | " + col4 + "   | " + col5 );
                }
            }
        } catch (SQLException e){
            System.out.println("There was an SQL issue: ");
            e.printStackTrace();
        }
    }

    public static void doDatabaseCategories(String username, String password, String sqlServerAddress) throws SQLException {

        try (
                Connection connection = DriverManager.getConnection(sqlServerAddress, username, password);
        )
        {

            try (
                    PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM categories ORDER BY categoryID;");
                    ResultSet results = pStatement.executeQuery();
            ) {
                while (results.next()) {
                    int col1 = results.getInt("categoryID");
                    String col2 = results.getString("CategoryName");

                    System.out.println(col1 + ". " + col2);
                }
            }

            int categoryID = Console.PromptForInt("Choose category ID: ");

            try(PreparedStatement getProductsStatement = connection.prepareStatement("SELECT productID, productName, UnitPrice, UnitsInStock from northwind.products WHERE products.categoryID = ?");){

                getProductsStatement.setInt(1, categoryID);

                try (ResultSet results = getProductsStatement.executeQuery();){
                    while (results.next()) {
                        int col1 = results.getInt("productID");
                        String col2 = results.getString("productName");
                        double col3 = results.getDouble("UnitPrice");
                        int col4 = results.getInt("UnitsInStock");
                        System.out.println(col1+ " | " + col2+ " | " + col3+ " | " + col4);
                    }
                }
            }

        }catch (SQLException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }
}










