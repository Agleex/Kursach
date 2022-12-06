import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {

//        SERVER

        try ( ServerSocket server = new ServerSocket(8000) ) {

            System.out.println("Server started!");

            while (true)
                try (
                        Socket socket = server.accept();
                        BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                        BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
                ) {

                } catch ( NullPointerException e ) {
                    e.printStackTrace();
                }

        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
    }
}