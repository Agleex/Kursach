package com.example.socket;

import java.io.*;
import java.net.Socket;

public class SocketReq {
    private static void send(BufferedWriter writer, String data) throws IOException {
         writer.write(data);
         writer.newLine();
         writer.flush();
    }

    static public void sendSingInRequest (String modalType, String login, String pass) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
        ) {

            send(writer, modalType);
            send(writer, login);
            send(writer, pass);

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
