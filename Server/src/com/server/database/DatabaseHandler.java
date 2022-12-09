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

    public ResultSet singIn(String login, String pass) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_LOGIN + " =? AND " + Const.USERS_PASSWORD + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            prSt.setString(2, pass);

            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
