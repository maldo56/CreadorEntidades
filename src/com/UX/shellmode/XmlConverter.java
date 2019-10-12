package com.UX.shellmode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.object.DtAttribute;
import com.object.DtEntity;

public class XmlConverter {

	Document doc = null; 
	
	
	public XmlConverter() {
		
	}
	
	public void loadFile(File xmlFile) {
		DocumentBuilder db;
		
		try {
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			doc = db.parse(xmlFile);
			doc.getDocumentElement().normalize();  
			
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public String getProyectFolder() {
		NodeList nodeList = this.doc.getElementsByTagName("ProyectFolderPath");
		Node node = nodeList.item(0);
		
		return node.getTextContent();
	}
	
	public String getTemplateFolder() {
		NodeList nodeList = this.doc.getElementsByTagName("TemplateFolderPath");
		Node node = nodeList.item(0);
		
		return node.getTextContent();
	}
	
	public String getPackage() {
		NodeList nodeList = this.doc.getElementsByTagName("Package");
		Node node = nodeList.item(0);
		
		return node.getTextContent();
	}
	
	public List<DtEntity> getEntities() {
		
		List<DtEntity> entities = null;
		
		if(this.doc != null) {
			entities = new ArrayList<DtEntity>();
			
			NodeList nodeListAux;
			Node nodeAux;
			NamedNodeMap namedNodeMap;
			
			DtEntity entity = null;
			DtAttribute attribute = null;
			
			
			// Get entities
			NodeList nodeList = this.doc.getElementsByTagName("Entity"); 
			
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				entity = new DtEntity();
				
				// Set entity name
				Node node = nodeList.item(itr);
				namedNodeMap = node.getAttributes();
				entity.setName(namedNodeMap.getNamedItem("name").getTextContent());
				
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {  
					Element eElement = (Element) node;
					
					// Get attributes
					nodeListAux = (NodeList) eElement.getElementsByTagName("Attribute");
					
					for(int x=0;x < nodeListAux.getLength(); x++) {
						nodeAux = nodeListAux.item(x);
						
						namedNodeMap = nodeAux.getAttributes();
						
						attribute = new DtAttribute();
						attribute.setName(namedNodeMap.getNamedItem("name").getTextContent());
						attribute.setType(namedNodeMap.getNamedItem("type").getTextContent());
						
						entity.addAttribute(attribute);
					}
					
				}  
				
				entities.add(entity);
			}
			
		} else {
			System.out.println("Error: this.doc == null");
		}
		
		return entities;
	}
	
	
}
