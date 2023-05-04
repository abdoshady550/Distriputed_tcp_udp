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

/**
 *
 * @author Abdo shady
 */
public class UDP_Server {
    public static void main(String[] args) {
          try {
            // create UDP socket
            DatagramSocket serverSocket = new DatagramSocket(1234);
            System.out.println("UDP server is Working " );

            while (true) {
                // receive a message from a client and respond to it
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(packet);
                String data = new String(packet.getData(), 0, packet.getLength()).trim();
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                if (data.equals("Ping")) {
                    byte[] response = "Pong\n".getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(response, response.length, clientAddress, clientPort);
                    serverSocket.send(responsePacket);
                } else {
                    byte[] response = "Not the right message\n".getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(response, response.length, clientAddress, clientPort);
                    serverSocket.send(responsePacket);
                }

                if (data.equals("Exit")) {
                    break;
                }
            }

            // close the socket
            serverSocket.close();
            System.out.println("UDP server stopped");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
