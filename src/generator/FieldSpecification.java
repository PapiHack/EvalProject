package generator;

public class FieldSpecification 
{
	private String name;
	private String type;
	
	public FieldSpecification() {}
	
	public FieldSpecification(String name, String type) 
	{
		this.setName(name);
		this.setType(type);
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getType() 
	{
		return this.type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}

}
