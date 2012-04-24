package main;

import java.util.HashMap;
import java.util.Map;

import org.hsqldb.analysis.SQLCommand;
import org.hsqldb.analysis.StatementTracker;
import org.hsqldb.jdbc.JDBCPreparedStatement;

import connection.DatabaseConnectionManager;

public class InjectionSimulator 
{
	private DatabaseConnectionManager manager;

	public InjectionSimulator(DatabaseConnectionManager connection)
	{
		this.manager = connection;
	}
	
	public void run(String payload)
	{
		String rawStatements = simulateVulnerableQuery(payload);
		
		Map<SQLCommand, Integer> masterMap = new HashMap<SQLCommand, Integer>();
		
		//Zero out all values for the master map.
		for(SQLCommand command : SQLCommand.values())
		{
			masterMap.put(command, 0);
		}
		
		//Execute all of the statements and merge results with master.
		//Because the prepared statement object won't parse multiple
		//; delimited queries, this hack is necessary to simulate 
		//an actual sql injection situation.
		for(String statement : rawStatements.split(";"))
		{
			mergeMaps(processSQLStatement(statement), masterMap);
		}
		
		for(SQLCommand command : SQLCommand.values())
		{
			System.out.println(command + " : " + masterMap.get(command));
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
	
	private Map<SQLCommand, Integer> processSQLStatement(String sql)
	{
		JDBCPreparedStatement statement = (JDBCPreparedStatement) manager.getPreparedStatement(sql);
		
		Map<SQLCommand, Integer> queryMap = StatementTracker.getMapForKey(statement.getSql());
		
		return queryMap;
	}
	
	private Map<SQLCommand, Integer> buildVulnerableQueryExpectationMap()
	{
		Map<SQLCommand, Integer> queryVector = new HashMap<SQLCommand, Integer>();
		
		//Only one SELECT is expected.
		queryVector.put(SQLCommand.SELECT, 1);
		
		return queryVector;
	}
	
	private String simulateVulnerableQuery(String parameter)
	{
		return "SELECT * FROM contacts WHERE phone = '" + parameter + "'";
	}
}
