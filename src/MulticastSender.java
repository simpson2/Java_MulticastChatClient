import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MulticastSender implements Runnable {

    //Attributes
    private Thread tS;
    private String tSName = "MulticastSender";

    DatagramSocket socket = null;
    DatagramPacket outPacket = null;
    byte[] outBuf;
    final int PORT = 8888;

    Scanner sc = new Scanner(System.in);

    public void run() {
        System.out.println("Running "+ tSName);

        try {
            socket = new DatagramSocket();
            String msg;

            while (true) {
                msg = sc.nextLine();
                outBuf = msg.getBytes();

                //Send to multicast IP address and port
                InetAddress address = InetAddress.getByName("224.2.2.3");
                outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);

                socket.send(outPacket);

                System.out.println("Server sends : " + msg);
            }
        } catch (IOException e) {
            System.out.println("ERROR. Socket closing.");
        }
    }

    public void start() {
        System.out.println("Starting "+ tSName);

        if(tS == null) {
            tS = new Thread(this, tSName);
        }
        tS.start();
    }
}
