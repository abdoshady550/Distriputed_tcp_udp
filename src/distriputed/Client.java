/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package distriputed;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Abdo shady
 */
public class Client {
    public static void main(String[] args)throws IOException{
        
        try{
        Socket s1=new Socket("Localhost",1234);  
         InputStream in = s1.getInputStream();
            OutputStream out = s1.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter a message to send to the server: ");
                String message = scanner.nextLine();
                out.write(message.getBytes());

                // receive response from the server
                byte[] buffer = new byte[1024];
                int length = in.read(buffer);
                String response = new String(buffer, 0, length);
                if (message.equals("Exit")) {
                    break;
                }
                System.out.println("Server response: " + response);
            }

            // close the socket
            s1.close();
            System.out.println("TCP client disconnected " );
        }catch (IOException e) {
            e.printStackTrace();
        }
  
    }
    
}
