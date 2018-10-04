package com.example.edilio.guitiming;


import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLParser {
	
	public String result = "";
	public ArrayList<AndroidWidget> arrayObj;
	private String type;
	private String id;
	
	public XMLParser(){
	}		
	
	public ArrayList<AndroidWidget> parse(File file) throws ParserConfigurationException, SAXException, IOException {
		arrayObj = new ArrayList<>();
		File fXmlFile = file;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		Element root = doc.getDocumentElement();
		result = root.getAttribute("Instruction");
		NodeList nList = doc.getElementsByTagName("Element");
		AndroidWidget obj = null;
		for(int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				type = eElement.getElementsByTagName("Type").item(0).getTextContent();
				id = eElement.getElementsByTagName("Id").item(0).getTextContent();
				String time = eElement.getElementsByTagName("Time").item(0).getTextContent();
				String priority = eElement.getElementsByTagName("Priority").item(0).getTextContent();
				switch(type) {
				case "Button": {
 					obj = new ButtonWid(type, id, Integer.parseInt(priority), Float.parseFloat(time), "null");
					break;
				}
				case "TextView": {
					obj = new AndroidWidget(type, id, Integer.parseInt(priority), Float.parseFloat(time));
					break;
				}
				case "EditText":{
					String text = eElement.getElementsByTagName("Text").item(0).getTextContent();
					obj = new EditTextWid(type, id, Integer.parseInt(priority), Float.parseFloat(time), text);
                    break;
				}
				case "ListView":{
					ArrayList<String> array = new ArrayList<>();
					for(int j = 0; j < eElement.getElementsByTagName("value").getLength(); j++) {
						array.add(eElement.getElementsByTagName("value").item(j).getTextContent());
					}
					obj = new ListViewWid(type, id, Integer.parseInt(priority), Float.parseFloat(time), array);
					break;
				}
					case "Spinner":{
						ArrayList<String> array = new ArrayList<>();
						for(int j = 0; j < eElement.getElementsByTagName("value").getLength(); j++) {
							array.add(eElement.getElementsByTagName("value").item(j).getTextContent());
						}
						obj = new SpinnerWidget(type, id, Integer.parseInt(priority), Float.parseFloat(time), array);
						break;
					}
				default:
					obj = new AndroidWidget(type,id,Integer.parseInt(priority),Float.parseFloat(time));
					break;
				}
				arrayObj.add(obj);
				}

		}
		return arrayObj;
	}
	


	
	public String getResult() {
		return result;
	}
}
