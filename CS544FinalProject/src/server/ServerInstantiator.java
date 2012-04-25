package server;

import org.hsqldb.server.Server;

/**
 * What could this possibly do?!?
 * 
 * .. oh wait..
 * 
 * @author DeepBlue
 *
 */
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
        server.setSilent(true);
        server.setLogWriter(null);
        server.setErrWriter(null);
        server.start();      
    }
    
    public static void stopServer()
    {
    	server.stop();
    }
}
