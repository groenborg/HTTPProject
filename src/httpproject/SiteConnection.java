/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Grønborg
 */
public class SiteConnection {

    /*
    * Link to TCP site
    */
    /// http://docs.oracle.com/javase/tutorial/networking/sockets/index.html 
    
    
    public void openUrlConnection() {
        try {

            URL myUrl = new URL("http://google.com");
            URLConnection myConnection = myUrl.openConnection();
            // myConnection.connect(); spørg henrik
            myConnection.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(myConnection.getOutputStream());
            out.write("hello world");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(myConnection.getInputStream()));
            String decodedString;
            while ((decodedString = in.readLine()) != null) {
                System.out.println(decodedString);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
