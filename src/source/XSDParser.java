package source;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XSDParser 
{
	DocumentBuilderFactory docBuilderFactory;
	DocumentBuilder docBuilder;
	Document document;
	ArrayList<XSDElement> xsdElements;
	
	public XSDParser() {
		this.xsdElements = new ArrayList<XSDElement>();
	}
	
	private boolean exists(String name)
	{
		for(XSDElement xsd : xsdElements)
		{
			if(xsd.getName().equals(name))
			{
				return true;
			}
		}
		return false;
	}
	
	private int getIndexByName(String name)
	{
		int i;
		int length = xsdElements.size();
		for(i = 0; i< length; i++)
		{
			if(xsdElements.get(i).getName().equals(name))
			{
				return i;
			}
		}
		return -1;
	}
	
	public XSDParser(String fileTarget) throws Exception {
		this();
		this.docBuilderFactory = DocumentBuilderFactory.newInstance();
		this.docBuilder = this.docBuilderFactory.newDocumentBuilder();
		this.document = this.docBuilder.parse (new File(fileTarget));
	}
	
	public ArrayList<XSDElement> parseShema() throws Exception, IOException
	{
		System.out.println("Parsing du schéma...");
		NodeList nodeList = document.getElementsByTagName("xs:element");
		NodeList nodeList1,nodeList2;
		Element element,child;
		int i,j,length1,index;
		int length = nodeList.getLength();
		XSDElement xsd;
		String name;
		for(i = 0 ; i < length ; i++)
		{
			element = (Element)nodeList.item(i);
			//System.out.println(element.getAttribute("name")+" "+element.getAttribute("type"));
			if(!element.getAttribute("type").equals(""))
			{
				name = element.getAttribute("name");
				if(exists(name))
				{
					index = getIndexByName(name);
					xsdElements.get(index).setType("simple");
				}
				else
				{
					xsd = new XSDElement();
					xsd.setType("simple");
					xsd.setName(element.getAttribute("name"));
					xsdElements.add(xsd);
				}
			}
			else
			{
				nodeList1 = element.getElementsByTagName("xs:complexType");
				if(nodeList1.getLength() > 0)
				{
					name = element.getAttribute("name");
					if(exists(name))
					{
						index = getIndexByName(name);
						xsdElements.get(index).setType("complexe");
						nodeList2 = element.getElementsByTagName("xs:element");
						length1 = nodeList2.getLength();
						for(j = 0 ; j < length1 ; j++)
						{
							child = (Element) nodeList2.item(j);
							xsdElements.get(index).addChild(child.getAttribute("ref"));
							name = child.getAttribute("ref");
							if(exists(name))
							{
								index = getIndexByName(name);
								xsdElements.get(index).setParentName(element.getAttribute("name"));
							}
							else
							{
								xsd = new XSDElement();
								xsd.setParentName(element.getAttribute("name"));
								xsd.setName(name);
								xsdElements.add(xsd);
							}
						}
					}
					else
					{
						xsd = new XSDElement();
						xsd.setType("complexe");
						xsd.setName(element.getAttribute("name"));
						xsdElements.add(xsd);
						nodeList2 = element.getElementsByTagName("xs:element");
						length1 = nodeList2.getLength();
						for(j = 0 ; j < length1 ; j++)
						{
							child = (Element) nodeList2.item(j);
							xsd.addChild(child.getAttribute("ref"));
							name = child.getAttribute("ref");
							if(exists(name))
							{
								index = getIndexByName(name);
								xsdElements.get(index).setParentName(element.getAttribute("name"));
							}
							else
							{
								xsd = new XSDElement();
								xsd.setParentName(element.getAttribute("name"));
								xsd.setName(name);
								xsdElements.add(xsd);
							}
						}
					}
					
				}
			}
		}
		System.out.println("Parsing terminé !");
		return xsdElements;
	}

}
