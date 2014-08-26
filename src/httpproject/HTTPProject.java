/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grønborg
 */
public class HTTPProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       SiteConnection s = new SiteConnection();
       
       s.openUrlConnection();

    }

    public void input() {

        try {
            URL url = new URL("http://oracle.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputline;
            printHTTPContent(url);

            while ((inputline = in.readLine()) != null) {
                System.out.println(inputline);
            }
            in.close();

        } catch (Exception ex) {
            Logger.getLogger(HTTPProject.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void printHTTPContent(URL url) {

        System.out.println("protocol: " + url.getProtocol());
        System.out.println("host: " + url.getHost());
        System.out.println("port: " + url.getPort());
        System.out.println("default port: " + url.getDefaultPort());
        System.out.println("path: " + url.getPath());
        System.out.println("user info: " + url.getUserInfo());
    }

}
