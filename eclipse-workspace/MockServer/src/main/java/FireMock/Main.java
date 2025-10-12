package FireMock;

import spark.Spark;

public class Main {
    public static void main(String[] args) {
        int port = 9999; // default
        MockServer server = new MockServer(port);
        
        server.start();
        System.out.println("Started");
    }
}

