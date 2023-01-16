import net.Client;

import java.io.IOException;

public class ExecuteClientMain {

    public static void main(String[] args) throws IOException {


        Client client = new Client();
        client.start("localhost", 8888);

    }
}
