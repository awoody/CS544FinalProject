package analysis;

import java.sql.Connection;
import java.util.Map;

public interface iQueryPlanMapper 
{
	/**
	 * Given a sql statement, a connection object, and a map, populates the map with 
	 * the number of times each SQLCommand in the map's key set 
	 * will be executed in a given query plan.
	 * 
	 * For API purposes: calls to this method are guaranteed to have the following properties:
	 * no parameter will be null.  The connection will be valid and already connected to a database.
	 * For all keys in the map, the value will be an instantiated integer with value 0.
	 * 
	 * 
	 * @param sqlStatement - The statement to be executed.
	 * @param connection - The connection to use when categorizing the query.
	 * @param commandMap - A map from SQL command to integer.
	 */
	public abstract void populateQueryMap(String sqlStatement, Connection connection, Map<SQLCommand, Integer> commandMap);
}
