package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hsqldb.analysis.SQLCommand;
import org.hsqldb.analysis.StatementTracker;
import org.hsqldb.jdbc.JDBCPreparedStatement;

import connection.DatabaseConnectionManager;
import connection.Result;

public class InjectionSimulator 
{
	private final DatabaseConnectionManager manager;
	private boolean profilingEnabled = false;
	
	public InjectionSimulator(DatabaseConnectionManager connection)
	{
		this.manager = connection;
	}
	
	public void enableQueryProfiling()
	{
		profilingEnabled = true;
	}
	
	public void disableQueryProfiling()
	{
		profilingEnabled = false;
	}
	
	private List<JDBCPreparedStatement> prepareSql(String sql, Map<SQLCommand, Integer> masterMap)
	{		
		List<JDBCPreparedStatement> allStatements = new ArrayList<JDBCPreparedStatement>();
		
		//Execute all of the statements and merge results with master.
		//Because the prepared statement object won't parse multiple
		//; delimited queries, this hack is necessary to simulate 
		//an actual sql injection situation.
		for(String statement : sql.split(";"))
		{
			allStatements.add(processSQLStatement(statement, masterMap));
		}
		
		return allStatements;
	}
	
	public void executeStatement(String sql, Map<SQLCommand, Integer> queryProfile) throws SQLInjectionException
	{
		Map<SQLCommand, Integer> master = generateMasterMap();
		List<JDBCPreparedStatement> statements = prepareSql(sql, master);
		
		boolean isCorrectQuery = compareMaps(queryProfile, master);
		
		if(!isCorrectQuery)
			throw new SQLInjectionException("SQL Injection Attempt Discovered.");
		
		for(JDBCPreparedStatement statement : statements)
		{
			manager.executeStatement(statement);
		}
	}
	
	public List<Result> executeStatementWithResults(String sql, Map<SQLCommand, Integer> queryProfile) throws SQLInjectionException
	{
		Map<SQLCommand, Integer> master = generateMasterMap();
		List<JDBCPreparedStatement> statements = prepareSql(sql, master);
		
		boolean isCorrectQuery = compareMaps(queryProfile, master);
		
		if(!isCorrectQuery)
			throw new SQLInjectionException("SQL Injection Attempt Discovered.");
		
		List<Result> allResults = new LinkedList<Result>();
		
		for(JDBCPreparedStatement statement : statements)
		{
			ResultSet results = manager.executeStatementWithResults(statement);
			allResults.addAll(createResultsFromResultSet(results));
			
			try
			{
				results.close();
				statement.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		return allResults;
	}
	
	private boolean compareMaps(Map<SQLCommand, Integer> queryProfile, Map<SQLCommand, Integer> analysisMap)
	{
		//Always return true if profiling is disabled.
		if(!profilingEnabled)
			return true;
		
		for(SQLCommand command : queryProfile.keySet())
		{
			Integer profileValue = queryProfile.get(command);
			Integer analysisValue = analysisMap.get(command);
			
			if(profileValue != analysisValue)
				return false;
		}
		
		for(SQLCommand command : analysisMap.keySet())
		{
			Integer profileValue = queryProfile.get(command);
			Integer analysisValue = analysisMap.get(command);
			
			if(profileValue != analysisValue)
				return false;
		}
		
		return true;
	}
	
	private Map<SQLCommand, Integer> generateMasterMap()
	{
		Map<SQLCommand, Integer> masterMap = new HashMap<SQLCommand, Integer>();
		
		//Zero out all values for the master map.
		for(SQLCommand command : SQLCommand.values())
		{
			masterMap.put(command, 0);
		}
		
		return masterMap;
	}
	
	private List<Result> createResultsFromResultSet(ResultSet resultSet)
	{
		List<Result> allResults = new LinkedList<Result>();
		
		try
		{
			while(resultSet.next())
			{
				allResults.add(new Result(resultSet));
			}
			
			return allResults;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Something shit the bed here.");	
		}	
	}
	
	private void mergeMaps(Map<SQLCommand, Integer> source, Map<SQLCommand, Integer> destination)
	{
		for(SQLCommand command : SQLCommand.values())
		{
			if(source.get(command) == null)
				continue;
					
			int destinationValue = destination.get(command);
			int sourceValue = source.get(command);
			destination.put(command, destinationValue + sourceValue);		
		}
	}
	
	private JDBCPreparedStatement processSQLStatement(String sql, Map<SQLCommand, Integer> masterMap)
	{
		JDBCPreparedStatement statement = (JDBCPreparedStatement) manager.getPreparedStatement(sql);
		
		Map<SQLCommand, Integer> queryMap = StatementTracker.getMapForKey(statement.getSql());
		
		mergeMaps(queryMap, masterMap);
		
		return statement;
	}
}
