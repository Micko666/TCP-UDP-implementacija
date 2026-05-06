import java.net.*;
import java.util.Random;

public class UDPSender {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress adresa = InetAddress.getByName("localhost");
        String[] poruke = {"Poruka 1: Hej", "Poruka 2: Kako si", "Poruka 3: Sta ima", "Poruka 4: Jesi li tu", "Poruka 5: Dovidjenja"};
        Random random = new Random();
        int primljeno = 0;
        for (int i = 0; i < 100; i++) {
            int indeks = random.nextInt(poruke.length);
            byte[] podaci = poruke[indeks].getBytes();
            DatagramPacket paket = new DatagramPacket(podaci, podaci.length, adresa, 9876);
            socket.send(paket);
            System.out.println("Poslano: " + poruke[indeks]);
            byte[] buffer = new byte[1024];
            DatagramPacket odgovor = new DatagramPacket(buffer, buffer.length);
            socket.receive(odgovor);
            String reply = new String(odgovor.getData(), 0, odgovor.getLength());
            if (reply != null && !reply.isEmpty()) primljeno++;
            System.out.println("Primljeno: " + reply);
        }
        System.out.println("Poslano:   100");
        System.out.println("Primljeno: " + primljeno);
        System.out.println("Izgubljeno: " + (100 - primljeno));
        socket.close();
    }
}