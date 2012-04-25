package connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Result 
{
	private final Object[] values;
	
	public Result(ResultSet resultSet)
	{
		try 
		{
			int objectCount = resultSet.getMetaData().getColumnCount();
			
			values = new Object[objectCount];
			
			for(int i=0; i < objectCount; i++)
			{
				values[i] = resultSet.getObject(i+1);
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Couldn't properly build a result.");		
		}	
	}
	
	public Object[] getValues()
	{
		return values;
	}
	
	public String toString()
	{
		String output = "";
		
		for(int i=0; i < values.length; i++)
		{
			output += values[i] + "\t";
		}
		
		return output;
	}
}
