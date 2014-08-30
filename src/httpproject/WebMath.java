/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpproject;

/**
 *
 * @author Grønborg
 */
public class WebMath {

    public static int getResult(String s) {
        int result = 0;
        s = s.replaceAll("\\s", "");
        String[] tokens = new String[s.length()];
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = Character.toString(s.charAt(i));
        }
        
        for (String string : tokens) {
            System.out.println(string);
        }
        
        switch (tokens[1]) {
            case "+":
                result = Integer.parseInt(tokens[0].trim()) + Integer.parseInt(tokens[2].trim());
                break;
            case "*":
                result = Integer.parseInt(tokens[0].trim()) * Integer.parseInt(tokens[2].trim());
                break;
            case "/":
                result = Integer.parseInt(tokens[0].trim()) / Integer.parseInt(tokens[2].trim());
                break;
            case "-":
                result = Integer.parseInt(tokens[0].trim()) - Integer.parseInt(tokens[2].trim());
                break;
        }
        return result;
    }

}
