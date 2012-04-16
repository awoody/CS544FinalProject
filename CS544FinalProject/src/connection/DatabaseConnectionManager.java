package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is a a barebones object used for the express purpose of handling
 * the database connections.  It uses two connections, one for regular
 * entries and one for repositories, and handles them in different ways.
 * 
 * For entries, requests are always handled on the thread that makes the 
 * request.  This includes querying for duplicates and subsequently inserting.
 * 
 * This object is useful to any program needing a database connection, and has
 * generic methods for executing any SQL queries.  It's constructor takes
 * a DatabaseConnectionDescriptor which is used by the connect() method to
 * connect to any database you specify, so this object is further usable
 * with any mySQL or JDBC database.
 * 
 * @author Alex Woody
 *
 */
public class DatabaseConnectionManager 
{
	private Connection entryConnection;
	
	/**
	 * Connects to the database.  Will throw a runtime exception if it fails,
	 * since this is considered to be an unrecoverable error by any program
	 * using a database; this is simply so the code isn't polluted with try/catch
	 * blocks but the nature of the exception and its purpose are still preserved.
	 * 
	 */
	public void connect() 
	{	
		try
		{
			Class.forName("org.hsqldb.jdbc.JDBCDriver");

			entryConnection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:1234/sample", "SA", "");
		}
		catch (SQLException e)
		{
			throw new RuntimeException("Fatal Error: Could not connect to the database.");
		}
		catch (ClassNotFoundException e) 
		{
			throw new RuntimeException("Fatal Error: Could not connect locate driver.");
		}		
	}
	
	/**
	 * Returns a boolean indicating whether or not both
	 * connections have been established.
	 * 
	 * @return - The connection status.
	 */
	public boolean isConnected()
	{
		boolean isConnected = false;
		
		try
		{
			isConnected = (!(entryConnection == null) || !entryConnection.isClosed());
		}
		catch (SQLException e) {}
		
		return isConnected;
	}
	
	/**
	 * Closes the connections, after first making sure
	 * that both repositories have been flushed.
	 * 
	 */
	public void closeConnection()
	{	
		try
		{
			entryConnection.close();
		}
		catch (SQLException e)
		{
			System.err.println("Couldn't close connection.");
			e.printStackTrace();
		}
	}
			
	/**
	 * Executes the SQL statement, which is provided as a string,
	 * and returns a result set.  Intended for queries which
	 * fetch data.
	 * 
	 * @param sqlStatement - The statement to execute.
	 * 
	 * @return - The results of the SQL query.
	 */
	public ResultSet executeStatementWithResults(String sqlStatement)
	{
		ResultSet returnSet = null;
		
		try
		{
			PreparedStatement insertStatement = entryConnection.prepareStatement(sqlStatement);
			returnSet = insertStatement.executeQuery();
		}
		catch (SQLException e)
		{
			System.err.println("executeStatementWithResults()] The Query " + sqlStatement + " has failed with message: " + e.getLocalizedMessage());
		}
		
		if(returnSet == null)
			throw new RuntimeException("Executed a query that returned no result set: " + sqlStatement);
			
		return returnSet;
	}
	
	/**
	 * Executes an SQL statement which is not expected
	 * to return a ResultSet, such as an insert, update,
	 * truncate, or metadata change.
	 * 
	 * @param sqlStatement 
	 */
	public void executeStatement(String sqlStatement)
	{
		try
		{
			PreparedStatement insertStatement = entryConnection.prepareStatement(sqlStatement);
			insertStatement.execute();
		}
		catch (SQLException e)
		{
			System.err.println("executeStatement()] The Query " + sqlStatement + " has failed with message: " + e.getLocalizedMessage());;
		}
	}
}
