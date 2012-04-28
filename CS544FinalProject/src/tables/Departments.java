package tables;

public class Departments extends Table
{
	public static Departments emptyDepartments()
	{
		Departments emptyTable = new Departments("DEPARTMENTS");
		emptyTable.addField(new Field("dept_no ", FieldType.INT, -1, true));
		emptyTable.addField(new Field("dept_name", FieldType.STRING, 40, false));

		return emptyTable;
	}
	
	public Departments(String targetTable) 
	{
		super(targetTable);
		
		
	}

}
