/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriputed;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Abdo shady
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException{
       
   try {
            // create TCP socket
 ServerSocket server=new ServerSocket(1234);
 System.out.println("TCP server is Working" );

            while (true) {
                // wait for a client to connect
                Socket clientSocket = server.accept();
                // receive and respond to messages from the client
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) != -1) {
                    String data = new String(buffer, 0, length).trim();
                    if (data.equals("Ping")) {
                        out.write("Pong\n".getBytes());
                    } else {
                        out.write("Not the right message\n".getBytes());
                    }
                    if (data.equals("Exit")) {
                        break;
                    }
                }
                // close the client connection
                clientSocket.close();
                System.out.println("TCP client disconnected from " + clientSocket.getRemoteSocketAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
                                  

      
        // TODO code application logic here
    }
    
}
