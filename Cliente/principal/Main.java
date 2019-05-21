package principal;

import java.net.*;

import java.io.*;
 
/**
 * This program demonstrates a client socket application that connects to
 * a web server and send a HTTP HEAD request.
 *
 * @author www.codejava.net
 */
public class Main {
 
    public static void main(String[] args) {
 
        URL url;
        try {
            url = new URL("http://localhost/");
            
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return;
        }
 
        String hostname = url.getHost();
        int port = 6666;
 
        try (Socket socket = new Socket(hostname, port)) {
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            BufferedReader br = new BufferedReader(new FileReader("ventas2.txt"));
            String linea;
            
            while ((linea = br.readLine()) != null) {
            	writer.println(linea);
            }
            
            br.close();

 
            InputStream input = socket.getInputStream();
 
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            String line;
 
            line = reader.readLine();
                System.out.println(line);
                socket.close();
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
