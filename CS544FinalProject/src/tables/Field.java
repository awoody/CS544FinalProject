package tables;

public class Field 
{
	private String name;
	private Object value;
	private FieldType type;
	private int length;
	private boolean isLengthDependent;
	private boolean isPk;
	
	public Field(String name, FieldType type, int length, boolean isPk)
	{
		this.name = name;
		this.type = type;
		this.length = length;
		this.isLengthDependent = (length > 0);
		this.isPk = isPk;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}
	
	public boolean isPk() 
	{
		return isPk;
	}

	public Object getValue()
	{
		return value;
	}
	
	public String getName() 
	{
		return name;
	}

	public FieldType getType() 
	{
		return type;
	}

	public int getLength() 
	{
		return length;
	}

	public boolean isLengthDependent() 
	{
		return isLengthDependent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isLengthDependent ? 1231 : 1237);
		result = prime * result + (isPk ? 1231 : 1237);
		result = prime * result + length;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (isLengthDependent != other.isLengthDependent)
			return false;
		if (isPk != other.isPk)
			return false;
		if (length != other.length)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
