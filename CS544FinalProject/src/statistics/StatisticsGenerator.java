package statistics;

import java.util.Map;
import java.util.Random;

import main.InjectionSimulator;

import org.hsqldb.analysis.SQLCommand;

import server.ServerInstantiator;
import connection.DatabaseConnectionManager;

public class StatisticsGenerator 
{
	private static boolean debug = false;
	
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
				
		for(int i=0; i < 100; i++)
		{
			boolean doInjection = r.nextBoolean();
			
			if(doInjection)
			{
				String injection = generator.randomInjectedQuery(randomInjection(r));
				//System.out.println(injection);
				Map<SQLCommand, Integer> profile = simulator.executeStatementProfileOnly(injection);
				printMap(profile, doInjection);
			}
			else
			{
				String query = generator.randomQuery();
				//System.out.println(query);
				Map<SQLCommand, Integer> profile = simulator.executeStatementProfileOnly(query);
				printMap(profile, doInjection);
			}
			
		}
	}
	
	private static String randomInjection(Random r)
	{
		int value = r.nextInt(InjectionList.injections.length);
		
		return InjectionList.injections[value];
	}
	
	private static void printMap(Map<SQLCommand, Integer> commands, boolean queryType)
	{
		for(SQLCommand command : SQLCommand.values())
		{
			System.out.print(command + " " + commands.get(command) + " || ");
		}
		
		System.out.print("INJECTION?" + " " + queryType);
		
		System.out.println();
	}
}
