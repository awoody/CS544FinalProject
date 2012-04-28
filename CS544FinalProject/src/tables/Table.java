package tables;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class Table 
{
	String targetTable;
	private List<Field> fields = new LinkedList<Field>();
	
	public Table(String targetTable)
	{
		this.targetTable = targetTable;
	}
	
	public String getName()
	{
		return targetTable;
	}
	
	public int fieldCount()
	{
		return fields.size();
	}
	
	public void addField(Field field)
	{
		fields.add(field);
	}
		
	public Field randomField(Random r)
	{
		int value = r.nextInt(fields.size());
		
		return fields.get(value);
	}
}
