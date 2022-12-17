package com.example.socket;

import com.example.company.Company;
import com.example.user.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class SocketReq {
    private static void send(BufferedWriter writer, String data) throws IOException {
         writer.write(data);
         writer.newLine();
         writer.flush();
    }

    private static User getUser(BufferedReader reader) throws IOException {
        String login = reader.readLine();
        String password = reader.readLine();
        String isAdmin = reader.readLine();

        return new User(login, password, isAdmin);
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

    static public String sendSingInRequest (String requestType, String login, String pass) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, requestType);
            send(writer, login);
            send(writer, pass);

            String res = reader.readLine();

            return res;

        } catch ( IOException e ) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

    static public String sendRegistrateRequest (String requestType, String login, String isAdmin, String pass) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, requestType);
            send(writer, login);
            send(writer, pass);
            send(writer, isAdmin);

            String res = reader.readLine();

            return res;

        } catch ( IOException e ) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

    static public ArrayList<User> sendUserControlRequest (String requestType) {
        ArrayList<User> users = new ArrayList<>();
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, requestType);

            int numberOfUsers = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numberOfUsers; i++) {
                users.add(getUser(reader));
            }

        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return users;
    }

    static public void sendDeleteRequest (String requestType, String login) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, requestType);
            send(writer, login);

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    static public String sendEditUserRequest (String requestType, String oldLogin, String login, String password, String isAdmin) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {
            send(writer, requestType);
            send(writer, oldLogin);
            send(writer, login);
            send(writer, password);
            send(writer, isAdmin);

            String message = reader.readLine();

            System.out.println(message);

            return message;

        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return "ERROR";
    }
    static public Company sendCompanyFormRequest (String requestType, String currentLogin) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, requestType);
            send(writer, currentLogin);

            Company currentCompany = getCompany(reader);

            return currentCompany;

        } catch ( IOException e ) {
            e.printStackTrace();
            return null;
        }
    }
    static public void sendUpdateCompanyRequest (String requestType, String currentLogin, Company currentCompany) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, requestType);
            send(writer, currentLogin);
            sendCompany(writer, currentCompany);

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    static public String sendCompanyInfoRequest (String requestType, String currentLogin) {
        String description = "error";
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, requestType);
            send(writer, currentLogin);

            description = reader.readLine();

        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return description;
    }
    static public void sendCompanyInfoUpdateRequest (String requestType, String login, String description) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, requestType);
            send(writer, login);
            send(writer, description);

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
