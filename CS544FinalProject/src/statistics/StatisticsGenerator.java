package statistics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Random;

import main.InjectionSimulator;

import org.hsqldb.analysis.SQLCommand;

import server.ServerInstantiator;
import connection.DatabaseConnectionManager;

/**
 * This object will generate random queries and then analyze them,
 * outputting the results.  The queries that are generated are drawn
 * from the allowed commands in the UserCommandList object.  So, 
 * the injections are 'simulated' against a theoretical user, who is
 * allowed to do any of the operations specified in the UserCommandList.
 * The only supported commands are UPDATE, DELETE and SELECT, or any subset
 * thereof.
 * 
 * Then, based on the injection ratio, occasional injection queries are generated,
 * which are drawn from the InjectionList object.  All injections in that list
 * will work with any random query that can be possibly generated, which is why 
 * the list of allowed user commands is somewhat limited.
 * 
 * To use this object, simply adjust the hyper parameters and then run this
 * object as a java program.
 * 
 * @author DeepBlue
 *
 */
public class StatisticsGenerator 
{
	private static boolean debug = false;
	
	//How many total samples to generate.
	private static int datapoints = 5000;
	
	//This is the ratio of injected queries to
	//normal queries.  So for every n queries,
	//denoted by the denominator, m of them,
	//denoted by the numerator will be injections.
	private static int ratioNumerator = 1;
	private static int ratioDenominator = 2;
	
	//The filename is automatically generated based on the list of
	//allowed user queries, and the hyper parameters above.
	private static String filename = "files/" + generateFileName();
	
	public static void main(String [] args)
	{		
		if(debug)
			testBranch();
		else
			liveBranch();

		System.exit(0);
	}
	
	private static void testBranch()
	{
		Random r = new Random(System.nanoTime());	
		RandomQueryGenerator generator = new RandomQueryGenerator(r);
		
		for(int i=0; i < 100; i++)
		{
			boolean doInjection = r.nextBoolean();
			
			if(doInjection)
			{
				System.out.println(generator.randomQuery());
			}
			else
			{
				System.out.println(generator.randomInjectedQuery(randomInjection(r)));
			}
		}
	}
	
	private static void liveBranch()
	{
		ServerInstantiator.startServer();	
		DatabaseConnectionManager manager;	
		manager = new DatabaseConnectionManager();
		manager.connect();
		InjectionSimulator simulator = new InjectionSimulator(manager);
	
		Random r = new Random(System.nanoTime());	
		RandomQueryGenerator generator = new RandomQueryGenerator(r);
				
		printColumnLabels();
		
		double injections = 0;
		double normals = 0;
		
		for(int i=0; i < datapoints; i++)
		{
			int value = r.nextInt(ratioDenominator);
				
			boolean doInjection = (value > (ratioNumerator - 1)) ? false : true;
			
			if(doInjection)
			{
				String injection = generator.randomInjectedQuery(randomInjection(r));
				//System.out.println(injection);
				Map<SQLCommand, Integer> profile = simulator.executeStatementProfileOnly(injection);
				printMap(profile, doInjection);
				injections++;
			}
			else
			{
				String query = generator.randomQuery();
				//System.out.println(query);
				Map<SQLCommand, Integer> profile = simulator.executeStatementProfileOnly(query);
				printMap(profile, doInjection);
				normals++;
			}
		}
		
		System.out.println("Generation complete. Did " + normals + " normal queries and " + injections + " injected queries. Ratio was " + (injections / (injections + normals)));
	}
	
	private static String generateFileName()
	{
		String value = datapoints + "_";
		
		for(SQLCommand command : UserCommandList.allowedCommands)
		{
			value += command + "_";
		}
		
		value += "RATIO_" + ratioNumerator + "_" + ratioDenominator + ".dat";
		
		return value;
	}
	
	private static String randomInjection(Random r)
	{
		int value = r.nextInt(InjectionList.injections.length);
		
		return InjectionList.injections[value];
	}
	
	public static void printColumnLabels()
	{
		String line = "";
		
		for(SQLCommand command : SQLCommand.values())
		{
			line += command + "\t";
		}
		
		line += "INJECTION?";
		
		System.out.println(line);
	}
	
	private static void printMap(Map<SQLCommand, Integer> commands, boolean queryType)
	{
		String line = "";
		
		for(SQLCommand command : SQLCommand.values())
		{
			line += commands.get(command) + "\t";
		}
		
		int isInjection = queryType ? 1 : 0;
		
		line += isInjection + "";
		
		writeToFile(line);
	}
	
	public static void writeToFile(String text) 
	{
        try 
        {
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename), true));
                bw.write(text);
                bw.newLine();
                bw.close();
        } 
        catch (Exception e) 
        {
        	
        }
}
}
