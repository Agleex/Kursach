import com.server.database.Const;
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
    private static final String SING_IN = "SING_IN", REGISTRATE = "REGISTRATE", USER_CONTROL = "USER_CONTROL";

    private static void send(BufferedWriter writer, String data) throws IOException {
        writer.write(data);
        writer.newLine();
        writer.flush();
    }

    private static void singIn(BufferedReader reader, BufferedWriter writer, DatabaseHandler db) throws IOException {
        String login = reader.readLine();
        String password = reader.readLine();

        System.out.println(login);
        System.out.println(password);

        ResultSet rs = db.singIn(login, password);

        try {
            if( rs.next() ) {
                String isAdmin = rs.getString(Const.USERS_ADMIN);

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
                users.add(new User(rs.getString(Const.USERS_LOGIN), rs.getString(Const.USERS_PASSWORD), rs.getString(Const.USERS_ADMIN)));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        String numberOfUsers = Integer.toString(users.size());
        send(writer, numberOfUsers);

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


                    String modalType = reader.readLine();
                    System.out.println(modalType);

                    switch (modalType) {
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