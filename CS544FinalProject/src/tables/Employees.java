package tables;

public class Employees extends Table
{
	public static Employees emptyEmployees()
	{
		Employees emptyTable = new Employees("EMPLOYEES");
		emptyTable.addField(new Field("emp_no", FieldType.INT, -1, true));
		emptyTable.addField(new Field("birth_date", FieldType.DATE, -1, false));
		emptyTable.addField(new Field("first_name", FieldType.STRING, 14, false));
		emptyTable.addField(new Field("last_name", FieldType.STRING, 16, false));
		emptyTable.addField(new Field("gender", FieldType.STRING, 2, false));
		emptyTable.addField(new Field("hire_date", FieldType.DATE, -1, false));
		
		return emptyTable;
	}
	
	public Employees(String targetTable) 
	{
		super(targetTable);
	}
	
	
}
