package server;

import java.io.PrintWriter;

import org.hsqldb.server.Server;

public class ServerInstantiator 
{
    private static Server server;

    public static void main(String [] args)
    {
    	startServer();
    }
    
    public static void startServer() 
    {
        server = new Server();
        server.setAddress("localhost");
        server.setDatabaseName(0, "sample");
        server.setDatabasePath(0, "file:database/sample");
        server.setPort(1234);
        server.setTrace(true);
        server.setLogWriter(new PrintWriter(System.out));
        server.start();      
    }
    
    public static void stopServer()
    {
    	server.stop();
    }
}
