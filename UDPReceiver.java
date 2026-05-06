import java.net.*;

public class UDPReceiver {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9876);
        System.out.println("UDP Receiver pokrenut na portu 9876...");
        byte[] buffer = new byte[1024];
        for (int i = 0; i < 100; i++) {
            DatagramPacket paket = new DatagramPacket(buffer, buffer.length);
            socket.receive(paket);
            String poruka = new String(paket.getData(), 0, paket.getLength());
            System.out.println("Primljeno: " + poruka);
            String odgovor = "ACK: " + poruka;
            byte[] odgovorBytes = odgovor.getBytes();

            DatagramPacket odgovorPaket = new DatagramPacket(
                odgovorBytes,
                odgovorBytes.length,
                paket.getAddress(),  
                paket.getPort()      
            );
            socket.send(odgovorPaket);
        }
        socket.close();
    }
}