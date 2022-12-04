import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 8000);
                BufferedWriter writer = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
                BufferedReader reader = new BufferedReader( new InputStreamReader( socket.getInputStream() ));
                ) {

            System.out.println("Connected to server!");
            String request = "something";
            writer.write(request);
            writer.newLine();
            writer.flush();

            System.out.println(reader.readLine());

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}