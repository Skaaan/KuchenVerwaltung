package net;

import java.io.*;
import java.net.Socket;

public class Client {

    public Client() {
    }

    public void start(String hostname, int port) throws IOException {
        Socket clientSocket = new Socket(hostname, port);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));


        String userInput;
        System.out.println(" :c Switch to insert mode\n :d Switch to delete mode\n :r Switch to display mode\n :u Switch to change mode\n :p Change to persistence mode\n");

        System.out.println("enter Command:");


        while ((userInput = stdIn.readLine()) != null) {

            // Send the message to the server
            out.println(userInput);
            // Print the response from the server
            System.out.println("Response from server: " + in.readLine());
            System.out.println("enter Command:");

        }
    }

}

