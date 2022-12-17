package com.server.database;

import com.server.company.Company;

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

        String select = "SELECT * FROM " + ConstUser.USERS_TABLE + " WHERE " + ConstUser.USERS_LOGIN + " =? AND " + ConstUser.USERS_PASSWORD + " =?";

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

        String select = "SELECT * FROM " + ConstUser.USERS_TABLE + " WHERE " + ConstUser.USERS_LOGIN + " =?";

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

        String select = "INSERT INTO " + ConstUser.USERS_TABLE + "(" + ConstUser.USERS_LOGIN + "," + ConstUser.USERS_PASSWORD
                + "," + ConstUser.USERS_ADMIN + ")" + "VALUES(?,?,?)";
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

        String insert = "INSERT INTO " + ConstCompany.COMPANY_TABLE + "(" + ConstCompany.USERS_LOGIN + ",`assets`, `cashFlow`, `currentAssets`, `equity`, `longTermDuties`, `percentageToBePaid`, `profitBeforeTax`, `retainedEarningsOfPreviousYears`, `revenue`, `revenueFromSales`, `shortTermLiabilities`, `tangibleAssets`, `undestributedProfits`, `workingСapital`) VALUES (?, 'пусто', 'пусто', 'пусто', 'пусто', 'пусто', 'пусто', 'пусто', 'пусто', 'пусто', 'пусто', 'пусто', 'пусто', 'пусто', 'пусто')";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, login);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        insert = "INSERT INTO " + ConstDescription.DESCRIPTION_TABLE + " (`login`, `description`) VALUES (?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, login);
            prSt.setString(2, "Введите описание компаниии");

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllUsers() {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + ConstUser.USERS_TABLE;

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

    public void deleteUser(String login) {
        String delete = "DELETE FROM " + ConstUser.USERS_TABLE + " WHERE " + ConstUser.USERS_LOGIN + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setString(1, login);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(String oldLogin, String login, String password, String isAdmin) {
        String update = "UPDATE " + ConstUser.USERS_TABLE + " SET " + ConstUser.USERS_LOGIN + " =?, " + ConstUser.USERS_PASSWORD + " =?, " + ConstUser.USERS_ADMIN + " =? WHERE " + ConstUser.USERS_TABLE + "." + ConstUser.USERS_LOGIN + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.setString(3, isAdmin);
            prSt.setString(4, oldLogin);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCurrentCompany(String login) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + ConstCompany.COMPANY_TABLE + " WHERE " + ConstCompany.USERS_LOGIN + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);

            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void updateCurrentCompany (Company currentCompany, String currentLogin) {
        String update = "UPDATE `company` SET " +
                "`assets`=?," +
                "`cashFlow`=?," +
                "`currentAssets`=?," +
                "`equity`=?," +
                "`longTermDuties`=?," +
                "`percentageToBePaid`=?," +
                "`profitBeforeTax`=?," +
                "`retainedEarningsOfPreviousYears`=?," +
                "`revenue`=?," +
                "`revenueFromSales`=?," +
                "`shortTermLiabilities`=?," +
                "`tangibleAssets`=?," +
                "`undestributedProfits`=?," +
                "`workingСapital`=?" +
                " WHERE `login`=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);

            prSt.setString(1, currentCompany.getAssets());
            prSt.setString(2, currentCompany.getCashFlow());
            prSt.setString(3, currentCompany.getCurrentAssets());
            prSt.setString(4, currentCompany.getEquity());
            prSt.setString(5, currentCompany.getLongTermDuties());
            prSt.setString(6, currentCompany.getPercentageToBePaid());
            prSt.setString(7, currentCompany.getProfitBeforeTax());
            prSt.setString(8, currentCompany.getRetainedEarningsOfPreviousYears());
            prSt.setString(9, currentCompany.getRevenue());
            prSt.setString(10, currentCompany.getRevenueFromSales());
            prSt.setString(11, currentCompany.getShortTermLiabilities());
            prSt.setString(12, currentCompany.getTangibleAssets());
            prSt.setString(13, currentCompany.getUndestributedProfits());
            prSt.setString(14, currentCompany.getWorkingСapital());
            prSt.setString(15, currentLogin);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getCompanyInfo(String login) {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + ConstDescription.DESCRIPTION_TABLE + " WHERE " + ConstDescription.DESCRIPTION_LOGIN + " =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);

            resultSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
    public void updateCompanyInfo (String login, String description) {
        String update = "UPDATE `companyinfo` SET `description` =? WHERE `companyinfo`.`login` =?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);

            prSt.setString(1, description);
            prSt.setString(2, login);

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}