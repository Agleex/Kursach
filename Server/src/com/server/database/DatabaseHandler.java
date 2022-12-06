package com.server.database;

import com.server.users.User;

import java.sql.*;

public class DatabaseHandler extends Config{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    public ResultSet getAllUsers() {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USERS_TABLE;

        try {
            PreparedStatement prStat = getDbConnection().prepareStatement(select);

            resultSet = prStat.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
