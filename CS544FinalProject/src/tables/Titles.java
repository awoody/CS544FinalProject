package tables;

public class Titles extends Table
{
	public static Titles emptyTitles()
	{
		Titles emptyTable = new Titles("TITLES");
		emptyTable.addField(new Field("emp_no", FieldType.INT, -1, true));
		emptyTable.addField(new Field("title", FieldType.STRING, 50, true));
		emptyTable.addField(new Field("from_date", FieldType.DATE, -1, true));
		emptyTable.addField(new Field("to_date", FieldType.DATE, -1, false));
		
		return emptyTable;
	}
	
	public Titles(String targetTable) 
	{
		super(targetTable);
	}

}
