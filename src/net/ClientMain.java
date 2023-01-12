package net;

import java.io.IOException;

public class ClientMain {

    public static void main(String[] args) throws IOException {

        // Connect the client to the server
        Client client = new Client();
        client.start("localhost", 8888);

    }
}
