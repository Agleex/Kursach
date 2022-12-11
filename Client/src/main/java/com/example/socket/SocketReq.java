package com.example.socket;

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

        return new User(reader.readLine(), reader.readLine(), reader.readLine());
    }

    static public String sendSingInRequest (String modalType, String login, String pass) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, modalType);
            send(writer, login);
            send(writer, pass);

            String res = reader.readLine();

            return res;

        } catch ( IOException e ) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

    static public String sendRegistrateRequest (String modalType, String login, String isAdmin, String pass) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, modalType);
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

    static public ArrayList<User> sendUserControlRequest (String modalType) {
        ArrayList<User> users = new ArrayList<>();
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, modalType);

            int numberOfUsers = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numberOfUsers; i++) {

            }

        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return users;
    }
}
