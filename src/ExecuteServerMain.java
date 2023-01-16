import net.Server;

import java.io.IOException;

public class ExecuteServerMain {
    public static void main(String[] args) throws IOException {

        Server server = new Server();
        server.start(8888);


    }
}
