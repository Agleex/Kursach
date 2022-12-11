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

    public boolean isExist(String login) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_LOGIN + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);

            resultSet = prSt.executeQuery();

            if (resultSet.next())
                return true;
            else
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    public void registrate(String login, String pass, String isAdmin) {

        String select = "INSERT INTO " + Const.USERS_TABLE + "(" + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD
                + "," + Const.USERS_ADMIN + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            prSt.setString(2, pass);
            prSt.setString(3, isAdmin);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllUsers() {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Const.USERS_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
