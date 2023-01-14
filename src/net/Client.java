package net;

import java.io.*;
import java.net.Socket;

public class Client {

    public void start(String hostname, int port) throws IOException {
        Socket clientSocket = new Socket(hostname, port);
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));


        String userInput;
        System.out.println("""
                     :c Wechsel in den Einfügemodus
                     :d Wechsel in den Löschmodus
                     :r Wechsel in den Anzeigemodus
                     :u Wechsel in den Änderungsmodus
                    """);
        System.out.println("enter Command:");


        while ((userInput = stdIn.readLine()) != null) {

            // Send the message to the server
            out.println(userInput);
            // Print the response from the server
            System.out.println("Response from server: " + in.readLine());
            System.out.println("enter Command");

        }
    }

}
