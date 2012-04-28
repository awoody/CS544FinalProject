package statistics;

import java.sql.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.hsqldb.analysis.SQLCommand;

import tables.DepartmentEmployee;
import tables.DepartmentManager;
import tables.Departments;
import tables.Employees;
import tables.Field;
import tables.FieldType;
import tables.Salaries;
import tables.Table;
import tables.Titles;

public class RandomQueryGenerator 
{
	private Table targetTable;
	private final Random r;
	
	public RandomQueryGenerator(Random r)
	{
		this.r = r;
	}
	
	/**
	 * Generates a random SELECT, DELETE or
	 * UPDATE query from a random table.
	 * 
	 * @return A Valid SQL Query
	 */
	public String randomQuery()
	{	
		this.targetTable = randomTable();
		
		switch(randomCommand())
		{
			case SELECT:
				return randomSelect(null);
			case DELETE:
				return randomDelete(null);
			case UPDATE:
				return randomUpdate(null);
		}
		
		throw new RuntimeException("Nothing left!");
	}
	
	/**
	 * Generates a random SELECT, DELETE or UPDATE
	 * query from a random table, placing the 
	 * provided injection randomly in a where clause.
	 * 
	 * @param injection
	 * @return A Valid SQL Query with an injection.
	 */
	public String randomInjectedQuery(String injection)
	{
		this.targetTable = randomTable();
		
		switch(randomCommand())
		{
			case SELECT:
				return randomSelect(injection);
			case DELETE:
				return randomDelete(injection);
			case UPDATE:
				return randomUpdate(injection);
		}
		
		throw new RuntimeException("Nothing left!");
	}
	
	private Table randomTable()
	{
		int table = r.nextInt(6);
		
		switch(table)
		{
			case 0:
				return DepartmentEmployee.emptyDepartentEmployee();
			case 1:
				return DepartmentManager.emptyDepartmentManager();
			case 2:
				return Departments.emptyDepartments();
			case 3:
				return Employees.emptyEmployees();
			case 4:
				return Salaries.emptySalaries();
			case 5:
				return Titles.emptyTitles();
		}
		
		throw new RuntimeException("Shouldn't get here.");
	}
	
	private SQLCommand randomCommand()
	{
		int command = r.nextInt(3);
		
		switch(command)
		{
			case 0:
				return SQLCommand.SELECT;
			case 1:
				return SQLCommand.UPDATE;
			case 2:
				return SQLCommand.DELETE;
		}
		
		throw new RuntimeException("Shouldn't get here.");
	}
	
	private String randomUpdate(String injection)
	{
		return "UPDATE " + targetTable.getName() + randomSet() + " " + randomWhere(injection) + ";";
	}
	
	private String randomDelete(String injection)
	{

		return "DELETE FROM " + targetTable.getName() + " " +  randomWhere(injection) + ";";

	}
	
	private String randomSelect(String injection)
	{
		if(injection == null)
		{
			String statement = "SELECT * FROM " + targetTable.getName() + " ";
		
			int addWhere = r.nextInt(5);
		
			if(addWhere <= 2)
				statement += randomWhere(null);
		
			return statement + ";";
		}
		else
		{
			String statement = "SELECT * FROM " + targetTable.getName() + " " + randomWhere(injection);
			return statement;
		}
	}
		
	private String randomWhere(String injection)
	{
		int orNumber = 2;
		int andNumber = 3;
		
		String statement = "WHERE ";
		
		Field randomField = targetTable.randomField(r);
		Field randomField2 = null;
		Field randomField3 = null;
		
		if(injection == null)
			statement += randomCondition(randomField);
		else
			statement += randomInjectedCondition(randomField, injection);
		
		int addMoreParameters = r.nextInt(5);
		
		if(addMoreParameters == orNumber && targetTable.fieldCount() > 1)
		{
			randomField2 = randomFieldNotInSet(randomField);
			statement += " OR " + randomCondition(randomField2);
		}
		
		if(addMoreParameters == andNumber && targetTable.fieldCount() > 2)
		{
			randomField3 = randomFieldNotInSet(randomField, randomField2);
			statement += " AND " + randomCondition(randomField3);
		}
		
		return statement;
	}
	
	private Field randomFieldNotInSet(Field ... fields)
	{
		Set<Field> fieldsSet = new HashSet<Field>();
		
		for(Field f : fields)
			fieldsSet.add(f);
			
		int count = 0;
				
		while(true)
		{
			Field random = targetTable.randomField(r);
			
			if(!fieldsSet.contains(random))
				return random;
			
			count++;
			
			if(count >= 100)
				throw new RuntimeException("You are probably attempting to find a new field from a table wehre all fields have been found.");
		}
		
	}
	
	private String randomSet()
	{
		String statement = " SET ";
				
		Field randomField = targetTable.randomField(r);
		
		statement += randomSetValue(randomField);
		
		boolean addMoreParameters = r.nextBoolean();
		
		Field newRandomField = null;
		
		if(addMoreParameters && targetTable.fieldCount() > 1)
		{
			newRandomField = randomFieldNotInSet(randomField);
			
			statement += " , " + randomSetValue(newRandomField);
		}
		
		int oneLastParameter = r.nextInt(10);
		
		Field lastRandomField = null;
		
		if(oneLastParameter > 7 && targetTable.fieldCount() > 2)
		{	
			lastRandomField = randomFieldNotInSet(randomField, newRandomField);
						
			statement += " , " + randomSetValue(lastRandomField);
		}
		
		return statement + " ";
	}
	
	private String randomSetValue(Field randomField)
	{
		return randomField.getName() + " = " + randomValueForFieldType(randomField.getType(), randomField.getLength());
	}
	
	private String randomCondition(Field randomField)
	{
		return randomField.getName() + randomOperator() + randomValueForFieldType(randomField.getType(), randomField.getLength());
	}
	
	private String randomInjectedCondition(Field randomField, String injection)
	{
		return randomField.getName() + randomOperator() + randomValueForFieldType(randomField.getType(), randomField.getLength()) + injection;
	}
	
	private String randomOperator()
	{
		int operator = r.nextInt(2);
		
		switch(operator)
		{
			case 0:
				return " = ";
			case 1:
				return " != ";
			case 2:
				return " LIKE ";
		}
		
		throw new RuntimeException("Nope");
	}
	
	private Object randomValueForFieldType(FieldType type, int length)
	{
		switch(type)
		{
			case STRING:	
				return "'" + randomString(length) + "'";
			case INT:
				return r.nextInt(1000);
			case DATE:
				return randomDate();
		}
		
		throw new RuntimeException("Nope.");
	}
	
	//Randomly modify the current time by a substantially large
	//value to get random dates.
	private Date randomDate()
	{
		long currentTime = System.currentTimeMillis();
		
		long modifier = r.nextInt(60000000);
		
		long actual = currentTime - modifier;
		
		if(r.nextBoolean())
			actual = currentTime + modifier;
		
		return new Date(actual);
	}
	
	private String randomString(int length)
	{
		String random = "";
		
		for(int i=0; i < length; i++)
			random += randomCharacter();
	
		return random;
	}
	
	private char randomCharacter()
	{
		String alphabet = "abcdefghijklmnopqrstuvwxyz";

		return alphabet.charAt(r.nextInt(alphabet.length()));
	}
}

