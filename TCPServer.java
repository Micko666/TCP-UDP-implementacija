import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("TCP Server pokrenut na portu 1234...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Klijent se spojio: " + clientSocket.getInetAddress());

        BufferedReader in  = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter   out  = new PrintWriter(clientSocket.getOutputStream(), true);

        String message;
        while ((message = in.readLine()) != null) {
            System.out.println("Primljeno od klijenta: " + message);
            out.println("Server: " + message);
        }

        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
}