/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadedserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author Grønborg
 */
class WorkerRunnable implements Runnable {

    private Socket clientSocket = null;
    private String servertext = null;

    public WorkerRunnable(Socket clientSocket, String servertext) {
        this.clientSocket = clientSocket;
        this.servertext = servertext;
    }

    @Override
    public void run() {
        try {
            InputStream in = clientSocket.getInputStream();
            OutputStream outn = clientSocket.getOutputStream();

            BufferedReader irn = new BufferedReader(new InputStreamReader(in));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outn));

            
//            String line;
//            while((line = irn.readLine()) != null){
//                System.out.println(line);
//            }

  
            out.write("HTTP/1.2 200 OK\r\n");
            out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
            out.write("Server: Apache/0.8.4\r\n");
            out.write("Content-type: text/html; charset=utf-8 \r\n");
            out.write("Content-Length: 57\r\n");
            out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
            out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
            out.write("\r\n");
            out.write("<TITLE>Exemple</TITLE>");
            out.write("<P>Ceci est une page d'exemplå.</P>");
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static class DefaultHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            System.out.println(he.getRequestMethod());
        }

    }

}
