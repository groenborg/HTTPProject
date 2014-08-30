package httpproject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Grønborg
 */
public class WebServer {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String ip = "10.0.1.2";
        try {
            writeAddressToConsole(InetAddress.getByName(ip));
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(ip, 8014), 0);
            server.createContext("/", new DefaultHandler());
            server.setExecutor(null);
            server.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private static void writeAddressToConsole(InetAddress addr) {
        System.out.print("ip address inb byte: ");
        for (byte b : addr.getAddress()) {
            System.out.print(b);
        }
        System.out.println("");
        System.out.println("Canonical hostname: " + addr.getCanonicalHostName());
        System.out.println("host name: " + addr.getHostName());
        try {
            System.out.println("Is reachable in 5 sek: " + addr.isReachable(5));
        } catch (IOException e) {
        }
    }

    static class DefaultHandler implements HttpHandler {

        String fileFolder = "public/";

        @Override
        public void handle(HttpExchange he) throws IOException {

            String requestPath = he.getRequestURI().toString();
            System.out.println("Request URL: " + requestPath);
            String[] tmp = requestPath.split("\\.");
            String contentType = tmp[tmp.length-1];
            System.out.println("Content type: "+contentType);
            File file = new File(fileFolder + requestPath);
            byte[] bytesToSend = new byte[(int) file.length()];
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                bis.read(bytesToSend, 0, bytesToSend.length);
            } catch (Exception e) {

            }

            Headers h = he.getResponseHeaders();
            h.add("content-type", "text/"+contentType);
            he.sendResponseHeaders(200, bytesToSend.length);
            try (OutputStream os = he.getResponseBody()) {
                os.write(bytesToSend);
            }

        }

    }

}
