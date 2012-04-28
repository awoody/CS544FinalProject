package tables;

public class Salaries extends Table
{
	public static Salaries emptySalaries()
	{
		Salaries emptyTable = new Salaries("SALARIES");
		emptyTable.addField(new Field("emp_no", FieldType.INT, -1, true));
		emptyTable.addField(new Field("salary", FieldType.INT, -1, false));
		emptyTable.addField(new Field("from_date", FieldType.DATE, -1, true));
		emptyTable.addField(new Field("to_date", FieldType.DATE, -1, false));
		
		return emptyTable;
	}
	
	public Salaries(String targetTable)
	{
		super(targetTable);
	}
	
}
