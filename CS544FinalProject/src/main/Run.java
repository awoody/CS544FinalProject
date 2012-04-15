package main;

import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DatabaseConnectionManager;
import server.ServerInstantiator;

public class Run 
{
	private static DatabaseConnectionManager manager;
	private static boolean firstRun = false;
	
	public static void main(String [] args)
	{
		ServerInstantiator.startServer();
		manager = new DatabaseConnectionManager();
		manager.connect();
		
		if(firstRun)
			doSetup();
		
		System.out.println("Connected.");
		testQuery();
	}
	
	public static void doSetup()
	{
		String table1 = "CREATE TABLE contacts (name VARCHAR(45),email VARCHAR(45),phone VARCHAR(45));";
		manager.executeStatement(table1);
		
		String dummyInsert = "INSERT INTO contacts VALUES('bob', 'bob@loblaw.com', '505 123 4567');";
		manager.executeStatement(dummyInsert);
		
		
	}
	
	public static void testQuery()
	{
		String select = "SELECT * FROM contacts;";
		
		ResultSet results = manager.executeStatementWithResults(select);
		
		try 
		{		
			while(results.next())
			{
				String name = results.getString("name");
				String email = results.getString("email");
				String phone = results.getString("phone");
				
				System.out.println("Name: " + name);
				System.out.println("Email: " + email);
				System.out.println("Phone: " + phone);
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
