import com.server.company.Company;
import com.server.database.ConstCompany;
import com.server.database.ConstDescription;
import com.server.database.ConstUser;
import com.server.database.DatabaseHandler;
import com.server.users.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
//import java.sql.Connection;
//import java.sql.DriverManager;

public class Main {
    private static final String
            SING_IN = "SING_IN",
            REGISTRATE = "REGISTRATE",
            USER_CONTROL = "USER_CONTROL",
            DELETE_USER = "DELETE_USER",
            EDIT_USER = "EDIT_USER",
            COMPANY_FORM = "COMPANY_FORM",
            UPDATE_COMPANY = "UPDATE_COMPANY",
            GET_COMPANY_INFO = "GET_COMPANY_INFO",
            UPDATE_COMPANY_INFO = "UPDATE_COMPANY_INFO";

    private static void send(BufferedWriter writer, String data) throws IOException {
        writer.write(data);
        writer.newLine();
        writer.flush();
    }

    private static void sendUser(BufferedWriter writer, User user) throws IOException {
        send(writer, user.getLogin());
        send(writer, user.getPassword());
        send(writer, user.getAdmin());
    }
    private static void sendCompany(BufferedWriter writer, Company company) throws IOException {
        send(writer, company.getAssets());
        send(writer, company.getCashFlow());
        send(writer, company.getCurrentAssets());
        send(writer, company.getEquity());
        send(writer, company.getLongTermDuties());
        send(writer, company.getPercentageToBePaid());
        send(writer, company.getProfitBeforeTax());
        send(writer, company.getRetainedEarningsOfPreviousYears());
        send(writer, company.getRevenue());
        send(writer, company.getRevenueFromSales());
        send(writer, company.getShortTermLiabilities());
        send(writer, company.getTangibleAssets());
        send(writer, company.getUndestributedProfits());
        send(writer, company.getWorkingСapital());
    }
    private static Company getCompany(BufferedReader reader) throws IOException {
        String assets = reader.readLine();
        String cashFlow = reader.readLine();
        String currentAssets = reader.readLine();
        String equity = reader.readLine();
        String longTermDuties = reader.readLine();
        String percentageToBePaid = reader.readLine();
        String profitBeforeTax = reader.readLine();
        String retainedEarningsOfPreviousYears = reader.readLine();
        String revenue = reader.readLine();
        String revenueFromSales = reader.readLine();
        String shortTermLiabilities = reader.readLine();
        String tangibleAssets = reader.readLine();
        String undestributedProfits = reader.readLine();
        String workingСapital = reader.readLine();

        Company company = new Company(assets, cashFlow, currentAssets, equity, longTermDuties, percentageToBePaid,
                profitBeforeTax, retainedEarningsOfPreviousYears, revenue, revenueFromSales, shortTermLiabilities,
                tangibleAssets, undestributedProfits, workingСapital);

        return company;
    }
    private static void singIn(BufferedReader reader, BufferedWriter writer, DatabaseHandler db) throws IOException {
        String login = reader.readLine();
        String password = reader.readLine();

        System.out.println(login);
        System.out.println(password);

        ResultSet rs = db.singIn(login, password);

        try {
            if( rs.next() ) {
                String isAdmin = rs.getString(ConstUser.USERS_ADMIN);

                send(writer, isAdmin);
            } else {
                send(writer, "ERROR");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void registrate(BufferedReader reader, BufferedWriter writer, DatabaseHandler db) throws IOException {
        String login = reader.readLine();
        String isAdmin = reader.readLine();
        String password = reader.readLine();

        System.out.println("login: " + login);
        System.out.println("pass: " + password);
        System.out.println("isAdmin: " + isAdmin);

        if (!db.isExist(login)) {
            db.registrate(login, password, isAdmin);
            send(writer, "SUCCESS");
        } else send(writer, "ERROR");
    }
    private static void userControl(BufferedWriter writer, DatabaseHandler db) throws IOException {
        ResultSet rs = db.getAllUsers();
        ArrayList<User> users = new ArrayList<>();

        while (true) {
            try {
                if (!rs.next()) break;
                users.add(new User(rs.getString(ConstUser.USERS_LOGIN), rs.getString(ConstUser.USERS_PASSWORD), rs.getString(ConstUser.USERS_ADMIN)));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String numberOfUsers = Integer.toString(users.size());
        send(writer, numberOfUsers);

        for (int i = 0; i < users.size(); i++) {
            sendUser(writer, users.get(i));
        }

    }
    private static void deleteUser(BufferedReader reader, DatabaseHandler db) throws IOException {
        String login = reader.readLine();

        db.deleteUser(login);
    }
    private static void editUser(BufferedReader reader, BufferedWriter writer, DatabaseHandler db) throws IOException {
        String oldLogin = reader.readLine();
        String login = reader.readLine();
        String password =reader.readLine();
        String isAdmin = reader.readLine();

        if (db.isExist(login)) {
            send(writer,"ERROR");
            return;
        }

        db.updateUser(oldLogin, login, password, isAdmin);

        send(writer, "SUCCESS");
    }
    private static void getCurrentCompany(BufferedReader reader, BufferedWriter writer, DatabaseHandler db) throws IOException {
        String login = reader.readLine();

        ResultSet rs = db.getCurrentCompany(login);

        try {
            if (rs.next()) {
                String assets = rs.getString(ConstCompany.COMPANY_ASSETS);
                String cashFlow = rs.getString(ConstCompany.COMPANY_CASH_FLOW);
                String currentAssets = rs.getString(ConstCompany.COMPANY_CURRENT_ASSETS);
                String equity = rs.getString(ConstCompany.COMPANY_EQUITY);
                String longTermDuties = rs.getString(ConstCompany.COMPANY_LONG_TERM_DUTIES);
                String percentageToBePaid = rs.getString(ConstCompany.COMPANY_PERCENTAGE_TO_BE_PAID);
                String profitBeforeTax = rs.getString(ConstCompany.COMPANY_PROFIT_BEFORE_TAX);
                String retainedEarningsOfPreviousYears = rs.getString(ConstCompany.COMPANY_RETAINED_EARNINGS_OF_PREVIOUS_YEARS);
                String revenue = rs.getString(ConstCompany.COMPANY_REVENUE);
                String revenueFromSales = rs.getString(ConstCompany.COMPANY_REVENUE_FROM_SALES);
                String shortTermLiabilities = rs.getString(ConstCompany.COMPANY_SHORT_TERM_LIABILITIES);
                String tangibleAssets = rs.getString(ConstCompany.COMPANY_TANGIBLE_ASSETS);
                String undestributedProfits = rs.getString(ConstCompany.COMPANY_UNDESTRIBUTED_PROFITS);
                String workingСapital = rs.getString(ConstCompany.COMPANY_WORKING_СAPITAL);

                Company currentCompany = new Company(assets, cashFlow, currentAssets, equity, longTermDuties, percentageToBePaid,
                        profitBeforeTax, retainedEarningsOfPreviousYears, revenue, revenueFromSales, shortTermLiabilities,
                        tangibleAssets, undestributedProfits, workingСapital);

                currentCompany.printSmthng();

                sendCompany(writer, currentCompany);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void updateCurrentCompany(BufferedReader reader, DatabaseHandler db) throws IOException {
        String currentLogin = reader.readLine();
        Company currentCompany = getCompany(reader);

        db.updateCurrentCompany(currentCompany, currentLogin);
    }
    private static void getCompanyInfo(BufferedReader reader, BufferedWriter writer, DatabaseHandler db) throws IOException {
        String login = reader.readLine();

        ResultSet rs = db.getCompanyInfo(login);

        try {
            if (rs.next()) {
                String description = rs.getString(ConstDescription.DESCRIPTION_DESCRIPTION);

                send(writer, description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void updateCompanyInfo(BufferedReader reader, DatabaseHandler db) throws IOException {
        String login = reader.readLine();
        String description = reader.readLine();

        db.updateCompanyInfo(login, description);
    }
    public static void main(String[] args) {

        DatabaseHandler db = new DatabaseHandler();

//        SERVER

        try ( ServerSocket server = new ServerSocket(8000) ) {

            System.out.println("Server started!");

            while (true)
                try (
                        Socket socket = server.accept();
                        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                        BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
                ) {

                    String requestType = reader.readLine();
                    System.out.println(requestType);

                    switch (requestType) {
                        case SING_IN : {
                            singIn(reader, writer, db);
                            break;
                        }
                        case REGISTRATE: {
                            registrate(reader, writer, db);
                            break;
                        }
                        case USER_CONTROL: {
                            userControl(writer ,db);
                            break;
                        }
                        case DELETE_USER: {
                            deleteUser(reader, db);
                            break;
                        }
                        case EDIT_USER: {
                            editUser(reader, writer, db);
                            break;
                        }
                        case COMPANY_FORM: {
                            getCurrentCompany(reader, writer, db);
                            break;
                        }
                        case UPDATE_COMPANY: {
                            updateCurrentCompany(reader, db);
                            break;
                        }
                        case GET_COMPANY_INFO: {
                            getCompanyInfo(reader, writer, db);
                            break;
                        }
                        case UPDATE_COMPANY_INFO: {
                            updateCompanyInfo(reader, db);
                            break;
                        }

                        default: {
                            System.out.println("such type doesn't exist!");
                        }
                    }
                } catch ( NullPointerException e ) {
                    e.printStackTrace();
                }

        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
    }
}