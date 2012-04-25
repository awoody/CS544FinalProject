package main;

import java.sql.PreparedStatement;

import server.ServerInstantiator;
import connection.DatabaseConnectionManager;
import external.ExternalInterface;

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
		//String payload = "505 123 4567'; ALTER TABLE contacts DROP COLUMN phone; SELECT * FROM contacts WHERE '342 234 2342' IN (SELECT phone FROM contacts); INSERT INTO contacts VALUES('olol', 'bob@loblaw.com', '505 123 4567'); UPDATE contacts SET phone = '666 666 6666' WHERE name = 'sei-tan'; DELETE FROM contacts WHERE name = 'sei-tan'; SELECT * FROM contacts WHERE phone = '505 123 4567";
		String payload = "505 123 4567' OR 1 = '1";
			
		new ExternalInterface(new InjectionSimulator(manager)).run();
		
		System.exit(0);
	}
	
	//This defines the schema for the one table in the DB right now and also 
	//inserts some records, too.
	public static void doSetup()
	{
//		String clear = "DROP TABLE contacts;"; //Might fail but the rest works.
//		PreparedStatement statement = manager.getPreparedStatement(clear);
//		manager.executeStatement(statement);
		
		String table1 = "CREATE TABLE contacts (name VARCHAR(45),email VARCHAR(45),phone VARCHAR(45), PRIMARY KEY(name));";
		PreparedStatement statement = manager.getPreparedStatement(table1);
		manager.executeStatement(statement);
		
		String dummyInsert = "INSERT INTO contacts VALUES('bob', 'bob@loblaw.com', '505 123 4567');";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO contacts VALUES('steve', 'bob@loblaw.com', '505 123 4567');";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO contacts VALUES('mark', 'bob@loblaw.com', '505 123 4567');";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO contacts VALUES('betsy', 'bob@loblaw.com', '505 123 4567');";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		table1 = "CREATE TABLE addresses (street VARCHAR(512),houseNumber INT,zip INT, PRIMARY KEY(street, houseNumber, zip));";
		statement = manager.getPreparedStatement(table1);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO addresses VALUES('1220 Central NE', 15, 87106);";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO addresses VALUES('1000 Marquette NE', 1, 87106);";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO addresses VALUES('1 University of New Mexico', 1, 87106);";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
		
		dummyInsert = "INSERT INTO addresses VALUES('2 University of New Mexico', 1, 87106);";
		statement = manager.getPreparedStatement(dummyInsert);
		manager.executeStatement(statement);
	}
}
