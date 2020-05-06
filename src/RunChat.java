public class RunChat {
    public static void main(String[] args) {
        MulticastReceiver McR = new MulticastReceiver();
        MulticastSender McS = new MulticastSender();

        McR.start();
        McS.start();
    }
}
