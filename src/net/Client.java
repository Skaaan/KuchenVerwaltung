package net;

import java.io.*;
import java.net.Socket;

public class Client {

    public void start(String hostname, int port) throws IOException {
        Socket clientSocket = new Socket(hostname, port);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); //sendet msg from client to server
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //yesta9bel nachricht from server
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));


        String userInput;
        System.out.println("enterCommand");

        while ((userInput = stdIn.readLine()) != null) {

            // Send the message to the server
            out.println(userInput); //take entered kommand (string input) and send it to server
            // Print the response from the server
            System.out.println("Response from server: " + in.readLine()); //response is written back //in is buffer reader yesta9bel nachricht mel server lel client
            System.out.println("enterCommand");                                //out sendet, bufferreader testa9bel

        }
    }
}
