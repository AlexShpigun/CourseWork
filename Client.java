import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    private static final int  SERVER_PORT = 8000;
    private static String ipAddress = "localhost";

    public static void main(String[] args) throws IOException {

        Socket socket;
        socket = new Socket(ipAddress, SERVER_PORT);

        MassageGetter massageGetter = new MassageGetter(socket);
        massageGetter.start();



    }

}


class MassageGetter extends Thread {
    private BufferedReader in;
    public MassageGetter(Socket socket) {
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {

        }
    }
    @Override
    public void run() {
        while (true) {
            String request;
            try {
                request = in.readLine();
                System.out.println(request);

            } catch (IOException e) {
                break;
            }

        }

    }
}