package org.hsqldb.analysis;

import java.util.Map;

import org.hsqldb.Statement;
import org.hsqldb.StatementDMQL;
import org.hsqldb.SubQuery;

/**
 * This object does all the heavy lifting of actually parsing the statement
 * and figuring out what is in it.  The primary mechanism for this is
 * the statement type, and that is why the StatementTypeClassifier was
 * created.  Other than that, if a Statement is a subclass of StatementDMQL,
 * it may contain subqueries, all of which are assumed to be SELECT statements.
 * 
 * @author DeepBlue
 *
 */
public class StatementAnalyzer 
{
	private final Statement parentStatement;
	private Map<SQLCommand, Integer> analysisMap;
	
	StatementAnalyzer(Statement parentStatement)
	{
		this.parentStatement = parentStatement;
	}
	
	//Updates the provided map with information about
	//the statement that was passed to the constructor.
	void analyze(Map<SQLCommand, Integer> workingMap) 
	{
		analysisMap = workingMap;
		int type = parentStatement.getType();
		
		SQLCommand commandType = StatementTypeClassifier.classifyStatementTypeAsCommand(type);
		
		//If the type is unknown, crash the system to make it easier to see which 
		//queries are not yet supported by this system.  In this case, look at the
		//query you were trying to execute, and look at the number printed here.  Use
		//that information to update StatementTypeClassifier.
		if(commandType == SQLCommand.UNKNOWN)
			throw new RuntimeException("Unknown statement type: " + type);
				
		incrementMap(commandType, 1);
			
		String sql = parentStatement.getSQL();
		analyzeStatement(sql);
		
		//This class of statement permits subqueries.
		if(parentStatement instanceof StatementDMQL)
			handleStatementDMQL(commandType, (StatementDMQL) parentStatement);
				
	}
	
	private void analyzeStatement(String sql)
	{
		int numAnds = countOccurances(sql, "AND");
		int numOrs = countOccurances(sql, "OR");
		int numWhere = countOccurances(sql, "WHERE");
		
		incrementMap(SQLCommand.AND, numAnds);
		incrementMap(SQLCommand.OR, numOrs);
		incrementMap(SQLCommand.WHERE, numWhere);
	}
	
	private int countOccurances(String target, String searchString)
	{
		int i = target.indexOf(searchString);
		
		if(i == -1)
			return 0;
		
		int counter = 1;
		while(true)
		{
			i = target.indexOf(searchString, i+1);
			
			if(i == -1)
				break;
			
			counter++;
		}
		
		return counter;
	}
	
	//Iterates over the subqueries, processing each of them in turn.
	private void handleStatementDMQL(SQLCommand statementType, StatementDMQL statement)
	{	
		for(SubQuery query : statement.getSubqueries())
			handleSubQuery(statementType, query);
	}
	
	//This method currently just increments the select value; however the SubQuery
	//objects have more information.  If there are cases where the SubQuery object
	//isn't a select that are discovered, this is the appropriate place to handle
	//those cases.
	private void handleSubQuery(SQLCommand parentType, SubQuery query)
	{
		//The only permitted subqueries are selects?
		incrementMap(SQLCommand.SELECT, 1);
	}
		
	//Increments the map's value for the given key
	//by one.
	private void incrementMap(SQLCommand key, int occurances)
	{
		if(!analysisMap.containsKey(key))
		{
			analysisMap.put(key, occurances);
		}
		else
		{
			int value = analysisMap.get(key);
			analysisMap.put(key, value + occurances);
		}	
	}
}
