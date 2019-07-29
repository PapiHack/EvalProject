package source;

import java.util.ArrayList;

public class XSDElement 
{
	
	private String type;
	private String name;
	private ArrayList<String> childsNames;
	private String parentName;
	
	public XSDElement() 
	{
		this.type = null;
		this.name = null;
		this.childsNames = new ArrayList<String>();
		this.parentName =null;
	}

	public String getParentName() 
	{
		return parentName;
	}

	public void setParentName(String parentName) 
	{
		this.parentName = parentName;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public ArrayList<String> getChildsNames() 
	{
		return childsNames;
	}

	public void addChild(String childName) 
	{
		this.childsNames.add(childName);
	}

}
