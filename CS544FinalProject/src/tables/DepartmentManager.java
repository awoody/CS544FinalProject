package tables;

public class DepartmentManager extends Table
{
	public static DepartmentManager emptyDepartmentManager()
	{
		DepartmentManager emptyTable = new DepartmentManager("DEPT_MANAGER");
		emptyTable.addField(new Field("dept_no", FieldType.STRING, 4, true));
		emptyTable.addField(new Field("emp_no", FieldType.INT, -1, true));
		emptyTable.addField(new Field("from_date", FieldType.DATE, -1, false));
		emptyTable.addField(new Field("to_date", FieldType.DATE, -1, false));
		
		return emptyTable;
	}

	public DepartmentManager(String targetTable) 
	{
		super(targetTable);
		// TODO Auto-generated constructor stub
	}
	
}
