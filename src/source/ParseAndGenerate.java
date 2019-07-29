package source;

import java.util.ArrayList;

import generator.*;

public class ParseAndGenerate 
{
	
	public static void doIt(String file) 
	{

		ArrayList<XSDElement> xsd = new ArrayList<XSDElement>();
		ArrayList<ClassSpecification> classes = new ArrayList<ClassSpecification> ();
		try 
		{
			xsd = new XSDParser(file).parseShema();
			ClassSpecification root = new ClassSpecification();
			ClassSpecification cls = new ClassSpecification();
			for(XSDElement element : xsd)
			{
				if(element.getParentName() == null && element.getType().equals("complexe")) 
				{
					root.setName(element.getName());
					root.setRoot(true);
					root.setAttributes(new ArrayList<FieldSpecification>());
				}
				else if(element.getParentName().equals(root.getName()) && element.getType().equals("complexe")) 
				{
					cls.setName(element.getName());
					cls.setRoot(false);
					cls.setAttributes(new ArrayList<FieldSpecification>());
					root.getAttributes().add(new FieldSpecification(element.getName(), "ArrayList<"+ ("" + element.getName().charAt(0)).toUpperCase().concat(element.getName().substring(1, element.getName().length())) +">"));
					String parent = element.getName();
					for(XSDElement el : xsd) 
					{
						if(el.getParentName() == null)
							continue;
						if(el.getParentName().equals(parent) && el.getType().equals("simple")) 
						{
							cls.getAttributes().add(new FieldSpecification(el.getName(), "String"));
						}
					}
				}
			}
			classes.add(root); classes.add(cls);
			ClassGenerator generator = new ClassGenerator(classes);
			generator.generate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
