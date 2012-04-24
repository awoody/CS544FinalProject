package org.hsqldb.analysis;

import java.util.HashMap;
import java.util.Map;

/**
 * This object is responsible for maintaining the map from sql statements
 * to the maps that are created once they are analyzed.  This object would
 * need to be replaced by sockets to facilitate IPC/RPC if the server
 * and client weren't running on the same VM.
 * 
 * @author DeepBlue
 *
 */
public class StatementTracker 
{
	private final static Map<String, Map<SQLCommand, Integer>> processedStatementsMap = new HashMap<String, Map<SQLCommand, Integer>>();
	
	public static void registerStatementByKey(String sql)
	{
		processedStatementsMap.put(sql, new HashMap<SQLCommand, Integer>());
	}
	
	public static Map<SQLCommand, Integer> getMapForKey(String sqlKey)
	{
		return processedStatementsMap.get(sqlKey);
	}
}
