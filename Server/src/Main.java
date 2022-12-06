import com.server.database.Const;
import com.server.database.DatabaseHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {

        DatabaseHandler db = new DatabaseHandler();

        ResultSet rs = db.getAllUsers();

        while (true) {
            try {
                if (!rs.next()) break;

                String login = rs.getString(Const.USERS_LOGIN);
                System.out.println(login);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
//        SERVER

        try ( ServerSocket server = new ServerSocket(8000) ) {

            System.out.println("Server started!");

            while (true)
                try (
                        Socket socket = server.accept();
                        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                        BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
                ) {


//                    String modalType = reader.readLine();
//                    System.out.println(modalType);
//
//                    switch (modalType) {
//                        case "SING_IN" : {
//                            String login = reader.readLine();
//                            String password = reader.readLine();
//
//                            System.out.println(login);
//                            System.out.println(password);
//
//                            return;
//                        }
//                        default: {
//                            System.out.println("such type doesn't exist!");
//                        }
//                    }
                } catch ( NullPointerException e ) {
                    e.printStackTrace();
                }

        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
    }
}