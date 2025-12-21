package com.BG.swp.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clientEMMA {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; 
        // This is the **server’s address**.
        // “127.0.0.1” means your own computer (localhost).
        // So in this case, both the client and server run on the same computer.

        int port = 53192; 
        // This is the **server’s port number**.
        // The client must connect to the same port that the server is using.

        try (
            Socket socket = new Socket(serverAddress, port); 
            // This line connects the client to the server using the given address and port.

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            // This reads the user’s input from the keyboard.

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // This reads incoming messages **from the server** through the socket.

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            // This sends messages **to the server** through the socket.
        ) {

            System.out.println("Connected to chat server. Type messages:");

            // A new thread is started to always listen for incoming messages from the server.
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    // Continuously read messages sent by the server
                    while ((message = in.readLine()) != null) {
                        System.out.println("Message: " + message);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });

            receiveThread.start(); 
            // Start the thread so it can receive messages while the main program runs.

            String userInput;
            // Main loop to read user input and send it to the server.
            while ((userInput = input.readLine()) != null) {
                out.println(userInput);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
