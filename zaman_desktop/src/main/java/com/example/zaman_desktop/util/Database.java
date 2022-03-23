package com.example.zaman_desktop.util;

import com.example.zaman_desktop.IndexApplication;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    /**
     * Location of database
     */
    private static final String location = IndexApplication.class.getResource("database.db").toExternalForm();

    /**
     * Currently only table needed
     */
    private static final String requiredTable = "CurrentUser";

    public static boolean isOK() {
        if (!checkDrivers()) return false; //driver errors

        if (!checkConnection()) return false; //can't connect to db

        return createTables();
    }

    private static boolean checkDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            System.out.println("CheckDrivers(): Successful");
            return true;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not start SQLite Drivers");
            return false;
        }
    }

    private static boolean checkConnection() {
        try (Connection connection = connect()) {
            System.out.println("CheckConnection(): Successful");
            return connection != null;
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not connect to database");
            return false;
        }
    }


    private static boolean createTables() {
        String sqlString = "DROP TABLE IF EXISTS CurrentUser;\n" +
                "CREATE TABLE CurrentUser\n" +
                "(id int ,\n" +
                "firstName varchar(255),\n" +
                "lastName varchar(255),\n" +
                "email varchar(255),\n" +
                "verified bit,\n" +
                "token varchar(2000));";

        try(Connection connection = Database.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlString);
            System.out.println("CreateTables(): Successful");
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static void createSampleUser() {
        String sql = "INSERT INTO CurrentUser VALUES (1, 'Zubeir', 'Mohamed', 'myemail', 0, 'lol');";
        try(Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            // Execute a query
            System.out.println("Inserting records into the table...");
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() {
        String dbPrefix = "jdbc:sqlite:";
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbPrefix + location);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
        return connection;
    }
}
