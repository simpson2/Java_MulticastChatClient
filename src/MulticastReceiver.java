import java.io.*;
import java.net.*;

public class MulticastReceiver implements Runnable {

    //Attributes
    private Thread tR;
    private String tRName = "MulticastReceiver";

    MulticastSocket socket = null;
    DatagramPacket inPacket = null;
    byte[] inBuf = new byte[256];

    public void run() {
        System.out.println("Running "+ tRName);

        try {
            //Prepare to join multicast group
            socket = new MulticastSocket(8888);
            InetAddress address = InetAddress.getByName("224.2.2.3");
            socket.joinGroup(address);

            while (true) {
                inPacket = new DatagramPacket(inBuf, inBuf.length);
                socket.receive(inPacket);
                String msg = new String(inBuf, 0, inPacket.getLength());
                System.out.println("From " + inPacket.getAddress() + " Msg : " + msg);
            }
        } catch (IOException e) {
            System.out.println("ERROR. Socket closing.");
        }
    }

    public void start() {
        System.out.println("Starting "+ tRName);

        if(tR == null) {
            tR = new Thread(this, tRName);
        }
        tR.start();
    }
}
