import net.TCP.Client;

import java.io.IOException;

public class ExecuteClientMain {

    public  void main(String[] args) throws IOException {

        Client client = new Client();
        client.start("localhost", 8888);

    }
}
