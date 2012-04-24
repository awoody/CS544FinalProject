package org.hsqldb.analysis;

import java.util.Map;

import org.hsqldb.Statement;

/**
 * Provides a static entry point into the statement analysis code.
 * Passing any Statement object here is valid; the query and everything
 * else can be extracted from the parent object.
 * 
 * @author DeepBlue
 *
 */
public class StatementAnalyzerUtils 
{
	public static void analyzeStatement(Statement cs)
	{	
		//Obtain the map that has already been created for this statement, when
		//the query was first processed.
		Map<SQLCommand, Integer> workingMap = StatementTracker.getMapForKey(cs.getSQL());
	
		//Create an analyzer and analyze the statement.
		StatementAnalyzer analyzer = new StatementAnalyzer(cs);
		analyzer.analyze(workingMap);

	}
}
