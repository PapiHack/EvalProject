package source;

import java.util.ArrayList;

import generator.*;

public class ParseAndGenerate 
{
	
	public static void doIt(String file) 
	{
		ArrayList<XSDElement> xsd = new ArrayList<XSDElement>();
		ArrayList<ClassSpecification> classes = new ArrayList<ClassSpecification>();
		
		try 
		{
			xsd = new XSDParser(file).parseShema();
			ClassSpecification root = getRootClass(xsd);
			
			for(XSDElement element : xsd)
			{
				
				if(element.getParentName() != null) 
				{
					if(element.getType().equals("complexe") && element.getParentName().equals(root.getName()))
					{
						root.getAttributes().add(new FieldSpecification(element.getName(), "ArrayList<" + ("" + element.getName().charAt(0)).toUpperCase().concat(element.getName().substring(1, element.getName().length()))+ ">" ));
						classes.add(getClasse(xsd, element.getName(), classes));
					}
				}
			}
			classes.add(root);
			ClassGenerator generator = new ClassGenerator(classes);
			generator.generate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private static ClassSpecification getClasse(ArrayList<XSDElement> xd, String parent, ArrayList<ClassSpecification> classes) 
	{
		ClassSpecification cls = new ClassSpecification();
		cls.setAttributes(new ArrayList<FieldSpecification>());
		cls.setRoot(false);
		cls.setName(parent);
		
		for(XSDElement el : xd) 
		{
			if(parent.equals(el.getParentName()) && el.getType().equals("simple")) 
			{
				cls.getAttributes().add(new FieldSpecification(el.getName(), "String"));
			}
			if(parent.equals(el.getParentName()) && el.getType().equals("complexe")) 
			{
				ClassSpecification klass = getClasse(xd, el.getName(), classes);
				classes.add(klass);
				cls.getAttributes().add(new FieldSpecification(el.getName(), "ArrayList<" + ("" + el.getName().charAt(0)).toUpperCase().concat(el.getName().substring(1, el.getName().length()))+ ">" ));
			}
		}
		
		return cls;
	}
	
	private static ClassSpecification getRootClass(ArrayList<XSDElement> xd) 
	{
		ClassSpecification root = new ClassSpecification();
		root.setAttributes(new ArrayList<FieldSpecification>());
		
		for(XSDElement element : xd) 
		{
			if(element.getParentName() == null) 
			{
				root.setName(element.getName());
				root.setRoot(true);
				break;
			}
		}
		return root;
	}

}
