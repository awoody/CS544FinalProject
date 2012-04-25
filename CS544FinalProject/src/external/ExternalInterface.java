package external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hsqldb.analysis.SQLCommand;

import connection.Result;

import main.InjectionSimulator;
import main.SQLInjectionException;

/**
 * A hacky interface that prompts for input from the user
 * and then prints the results.  Fully vulnerable to SQL
 * injection.
 * 
 * @author DeepBlue
 *
 */
public class ExternalInterface 
{
	private final InjectionSimulator simulator;
	
	public ExternalInterface(InjectionSimulator simulator)
	{
		this.simulator = simulator;
	}
	
	public void run()
	{
		InputStreamReader inp = new InputStreamReader(System.in); 
	    BufferedReader br = new BufferedReader(inp);

	    String prompt = "Enter an Employee ID : ";
	    
	    while(true)
	    { 
	    	System.out.println(prompt);
	  
	    	String input = "";
	    	try 
	    	{   		
	    		input = br.readLine();	
	    	} 
	    	catch (IOException e) 
	    	{
	    		e.printStackTrace();
	    	}
	    	
	    	System.out.println(input);
	    	
	    	try 
	    	{
	    		//simulator.executeStatement(input, null);
				List<Result> results = simulator.executeStatementWithResults(getSQL(input), getQueryProfile());
				
				System.out.println("****************** RESULTS ********************");
				
				for(Result r : results)
					System.out.println(r);
						
				System.out.println("***********************************************");		
			} 
	    	catch (SQLInjectionException e) 
	    	{
				System.out.println("INTRUDER ALERT!");
			}    	
	    }   
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
	
	private Map<SQLCommand, Integer> getQueryProfile()
	{
		Map<SQLCommand, Integer> profile = generateMasterMap();
		
		profile.put(SQLCommand.SELECT, 1);
		profile.put(SQLCommand.WHERE, 1);
		
		return profile;
	}
	
	private String getSQL(String name)
	{
		return "SELECT * FROM EMPLOYEES WHERE emp_no = '" + name + "'";
	}
}
