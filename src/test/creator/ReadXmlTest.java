package test.creator;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXmlTest {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub

		File file = new File("");  
		
		//an instance of factory that gives a document builder  
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		
		//an instance of builder to parse the specified xml file  
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(file);  
		doc.getDocumentElement().normalize();  
		
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
		NodeList nodeList = doc.getElementsByTagName("Entity"); 
		
		NodeList nodeListAux;
		Node nodeAux;
		
		NamedNodeMap namedNodeMap;
		
		// nodeList is not iterable, so we are using for loop  
		for (int itr = 0; itr < nodeList.getLength(); itr++)   
		{  
			Node node = nodeList.item(itr);  
			System.out.println("\nNode Name :" + node.getNodeName());  
			
			if (node.getNodeType() == Node.ELEMENT_NODE)   
			{  
				Element eElement = (Element) node;
				
				nodeListAux = (NodeList) eElement.getElementsByTagName("Attribute");
				
				for(int x=0;x < nodeListAux.getLength(); x++) {
					nodeAux = nodeListAux.item(x);
					
					namedNodeMap = nodeAux.getAttributes();
					
					System.out.println(namedNodeMap.getNamedItem("name").getTextContent());
					System.out.println(namedNodeMap.getNamedItem("type").getTextContent());
					
				}
				
			}  
			
		}  
		
	}

}
