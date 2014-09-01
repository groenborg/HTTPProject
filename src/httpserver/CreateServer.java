/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;

/**
 *
 * @author Grønborg
 */
public class CreateServer {

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8014), 0);
            server.createContext("/", new DefaultHandler());
            System.out.println("Starting server");
            server.setExecutor(null);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class DefaultHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {

            String path = System.getProperty("user.dir");
            String response = "";
            System.out.println(path);
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();

            StringBuilder b = new StringBuilder();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    b.append("</br> file: ").append(file.getName());
                } else if (file.isDirectory()) {
                    b.append("</br> folder: ").append(file.getName());
                } else if (file.isHidden()) {
                    b.append("</br> hidden file: ").append(file.getName());
                }
            }
            response = b.toString();
            Headers h = he.getResponseHeaders();
            h.add("content-type", "text/html");
            he.sendResponseHeaders(200, response.length());
            
            try (OutputStreamWriter out = new OutputStreamWriter(he.getResponseBody())) {
                out.write(response);
                out.flush();
            }

        }
    }

}
