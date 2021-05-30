import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerThread extends Thread {
    private final File SOURCE_ROOT_FILE;
    private Map<String, CopyOnWriteArrayList<Integer>> index;
    private BufferedWriter out;
    private BufferedReader in;

    public ServerThread(Map<String, CopyOnWriteArrayList<Integer>> index, Socket socket, File SOURCE_ROOT_FILE) throws IOException {
        this.index = index;
        this.SOURCE_ROOT_FILE = SOURCE_ROOT_FILE;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }
    private void sendMessage(String message) throws IOException {
        out.write(message + "\n");
        out.flush();

    }



    @Override
    public void run() {
        try {
            while(true) {
                String request;

                sendMessage("What do you want,man?");

                request =in.readLine();
                request = request.toLowerCase(Locale.ROOT);
                if(!index.containsKey(request)) {
                    sendMessage("There's no such thing,boy nex...." + request);
                    sendMessage(" ");
                    continue;
                }
                for (Integer docId: index.get(request)) {
                    sendMessage(  SOURCE_ROOT_FILE.list()[docId]);
                }
                sendMessage(" ");


            }
        }catch(IOException e) {}

    }
}






