package analysis;

import java.sql.Connection;
import java.util.Map;

public abstract class AbstractQueryPlanMapper 
{
	public abstract void populateQueryMap(String sqlStatement, Connection connection, Map<SQLCommand, Integer> commandMap);
}
