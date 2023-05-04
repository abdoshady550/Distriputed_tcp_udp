/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distriputed;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Abdo shady
 */
public class UDP_Client {
    public static void main(String[] args) {
         try {
            // create UDP socket
            DatagramSocket socket = new DatagramSocket();
            System.out.println("UDP client started");

            // send messages to the server
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter a message to send to the server: ");
                String message = scanner.nextLine();
                byte[] data = message.getBytes();
                InetAddress serverAddress = InetAddress.getByName("localhost");
                DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 1234);
                socket.send(packet);

                // receive response from the server
                byte[] buffer = new byte[1024];
                DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(responsePacket);
                String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                if (message.equals("Exit")) {
                    break;
                }
                System.out.println("Server response: " + response);
            }

            // close the socket
            socket.close();
            System.out.println("UDP client stopped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
