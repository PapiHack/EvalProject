package generator;

import java.util.ArrayList;

public class ClassSpecification 
{
	private String name;
	private boolean root = false;
	private ArrayList<FieldSpecification> attributes = new ArrayList<FieldSpecification>();
	
	public ClassSpecification() {}
	
	public ClassSpecification(String name, ArrayList<FieldSpecification> attributes) 
	{
		this.setName(name);
		this.setAttributes(attributes);
	}
	
	public String getName() 
	{
		return this.name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public boolean isRoot() 
	{
		return this.root;
	}
	
	public void setRoot(boolean root) 
	{
		this.root = root;
	}
	
	public ArrayList<FieldSpecification> getAttributes()
	{
		return this.attributes;
	}
	
	public void setAttributes(ArrayList<FieldSpecification> attributes) 
	{
		this.attributes = attributes;
	}
}
