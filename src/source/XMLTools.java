package source;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLTools 
{
	
	private Element rootElement;
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	
	public XMLTools() throws Exception 
	{
		this.documentBuilderFactory = DocumentBuilderFactory.newInstance();
		this.documentBuilder = documentBuilderFactory.newDocumentBuilder();
		this.document = documentBuilder.newDocument();
	}
	
	public XMLTools(String rootElementName) throws Exception
	{
		this();
		this.rootElement = document.createElement(rootElementName);
		this.document.appendChild(this.rootElement);
	}
	
	public void setElementToRootElement(String name)
	{
		Element element = document.createElement(name);
		this.rootElement.appendChild(element);
	}
	
	public void setElementToRootElement(String name,String content)
	{
		Element element = document.createElement(name);
		element.appendChild(document.createTextNode(content));
		this.rootElement.appendChild(element);
	}
	
	public void setElementToElement(String parentElementName,String newElementName)
	{
		NodeList nodeList = document.getElementsByTagName(parentElementName);
		Node node = nodeList.item(nodeList.getLength()-1);
		Element parentElement = (Element) node;
		Element newElement = document.createElement(newElementName);
		parentElement.appendChild(newElement);
	}
	
	public void setElementToElement(String parentElementName,String newElementName,String content)
	{
		NodeList nodeList = document.getElementsByTagName(parentElementName);
		Node node = nodeList.item(nodeList.getLength()-1);
		Element parentElement = (Element) node;
		Element newElement = document.createElement(newElementName);
		newElement.appendChild(document.createTextNode(content));
		parentElement.appendChild(newElement);
	}
	
	public void setAttributeToElement(String elementName,String attributeName,String value)
	{
		NodeList nodeList = document.getElementsByTagName(elementName);
		Node node = nodeList.item(nodeList.getLength()-1);
		Element element = (Element) node;
		element.setAttribute(attributeName, value);
		this.rootElement.appendChild(element);
	}
	
	public void setAttributeToRootElement(String attributeName, String value)
	{
		this.rootElement.setAttribute(attributeName, value);
	}
	
	public void createXML(String fileName) throws Exception
	{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(this.document);
		StreamResult streamResult = new StreamResult(new File("resources/generated/"+fileName+".xml"));
		
		transformer.transform(source, streamResult);
	}
}
