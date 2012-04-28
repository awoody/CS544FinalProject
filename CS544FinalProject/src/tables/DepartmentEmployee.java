package tables;

public class DepartmentEmployee extends Table
{
	public static DepartmentEmployee emptyDepartentEmployee()
	{
		DepartmentEmployee emptyTable = new DepartmentEmployee("DEPT_EMP");
		emptyTable.addField(new Field("emp_no", FieldType.INT, -1, true));
		emptyTable.addField(new Field("dept_no", FieldType.STRING, 4, true));
		emptyTable.addField(new Field("from_date", FieldType.DATE, -1, false));
		emptyTable.addField(new Field("to_date", FieldType.DATE, -1, false));
		
		return emptyTable;
	}
	
	public DepartmentEmployee(String targetTable) 
	{
		super(targetTable);
	}
}
