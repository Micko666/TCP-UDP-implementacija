import java.io.*;
import java.net.*;
import java.util.Random;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Spojen na TCP Server.");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String[] poruke = {"Poruka 1: Hej", "Poruka 2: Kako si", "Poruka 3: Sta ima", "Poruka 4: Jesi li tu", "Poruka 5: Dovidjenja"};
        Random random = new Random();
        int brojac = 1;
        int prim = 0;
        for (int i = 0; i < 100; i++) {
            int indeks = random.nextInt(poruke.length);
            out.println(poruke[indeks]);
            String reply = in.readLine();
            if (reply != null) prim++;
            System.out.println("Poslano:   poruka broj " + brojac + " " + poruke[indeks]);
            System.out.println("Primljeno: Odgovor " + brojac + " " + reply);
            brojac++;
        }
        System.out.println("Poslano:   100");
        System.out.println("Primljeno: " + prim);
        System.out.println("Izgubljeno: " + (100 - prim));
        socket.close();
    }
}