package com.BG.swp.lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class chatServer {
    // Server port number
    private static final int PORT = 53192;

    // Set to store output streams of all connected clients for broadcasting messages
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Server started on port " + PORT);

        // Try-with-resources to automatically close the server socket
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // Keep the server running indefinitely
            while (true) {
                // Accept a new client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                // Start a new thread to handle communication with this client
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print any exception that occurs while starting or running the server
        }
    }

    // Inner class to handle client communication
    static class ClientHandler implements Runnable {
        private Socket socket;          // Client socket
        private PrintWriter out;        // To send messages to the client
        private BufferedReader in;      // To read messages from the client

        // Constructor to set the client socket
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Initialize input stream to read messages from the client
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                // Initialize output stream to send messages to the client
                // 'true' enables auto-flushing so messages are sent immediately
                out = new PrintWriter(socket.getOutputStream(), true);

                // Add this client's writer to the set in a thread-safe way
                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                String message;
                // Continuously read messages from the client until they disconnect
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    broadcast(message); // Send the message to all connected clients
                }
            } catch (IOException e) {
                System.out.println("Client disconnected");
            } finally {
                // Cleanup when client disconnects
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Remove this client's writer from the set in a thread-safe way
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
            }
        }

        // Method to send a message to all connected clients
        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
