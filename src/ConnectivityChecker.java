import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectivityChecker {

    /**
     * Checks if there is an active internet connection.
     *
     * @return true if internet connection is available, false otherwise
     */
    public static boolean isInternetAvailable() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("google.com", 80), 2000);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
