package vxmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	HashMap<String, String> ScriptHM;
	HashMap<String, String> FieldHM;
	HashMap<String, String> VarHM;
	
	String lastPrompt = "";
	
	// constructor
	public Parser(File filename) {
		
		//hashmap to store Scripts
		this.ScriptHM = new HashMap<String,String>();
		
		//hashmap to store fieldVariables
		this.FieldHM = new HashMap<String,String>();
		
		this.VarHM = new HashMap<String, String>();
		
		
		//initialize the DBFactory for parsing
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			//create new documentbuilder using the factory
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			//parse the file into a Document
			Document doc = dBuilder.parse(filename);
			//Puts all Text nodes in the full depth of the sub-tree underneath this Node into 
			//a normal form (structures between text nodes)
			//doc.getDocumentElement().normalize();
			
			//get Number of VXMLs Tags, if more than 1, return Error, 
			NodeList vxmlNL = doc.getElementsByTagName("vxml");
			if(vxmlNL.getLength() != 1)
			{
				System.out.println("Error, Corrupted VXML File");
				System.exit(0);
			}
			
			//type cast the vxml tag into an element to access the children
			Node vxmlElement = (Node) vxmlNL.item(0);
			
			//Loop for all children inside VXML
			for(int i = 0; i < vxmlElement.getChildNodes().getLength(); i++)
			{
				//get every child
				Node e =  vxmlElement.getChildNodes().item(i);
				//skip #comments and empty lines
				if(e.getNodeName().startsWith("#"))
				{
					continue;
				}
				
				System.out.println(e.getNodeName());
				
				switch(e.getNodeName()){
				case "script": parseScript(e);break;
				case "form": this.parseForm(e);break; 
				//case "noinput": ;break; there are no active grammars in the vxml tag
				//case "nomatch": ;break; there are no active grammars in the vxml tag
				case "var": parseVar(e);break;//var/
				case "help": ;break;
				}
					
			}
			
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		} 
	}
	public void parseBlock(Node e)
	{
		for(int i = 0; i < e.getChildNodes().getLength(); i++)
		{
			Node blockcontent = e.getChildNodes().item(i);
			//skip #comments and empty lines
			if(blockcontent.getNodeName().startsWith("#"))
			{
				continue;
			}
			
			System.out.println(blockcontent.getNodeName());
			
			switch(blockcontent.getNodeName()){
			case "goto": ;break;
			case "if": parseIf(blockcontent);break;
			case "prompt": this.Prompt(blockcontent);break;
			case "reprompt": this.Reprompt();break;
			case "script": this.parseScript(blockcontent);;break; 
			case "var": this.parseVar(blockcontent);break;//var/
			case "audio": ;break;
			}
		}
	}
	public void parseIf(Node e)
	{
		boolean IfConditionisTrue = true;
		boolean elseIfvisited = false;
		//assuming If always has a valid condition
		String condition = e.getAttributes().getNamedItem("cond").getNodeValue();
		String firstOP = condition.split("==")[0].replace(" ", "").replace("\'","");
		String secondOP = condition.split("==")[1].replace(" ", "").replace("\'","");
		
		if(FieldHM.get(firstOP) != secondOP){ //not equal
			IfConditionisTrue = false;
		}
		
		//continue parsing
		for(int i = 0; i < e.getChildNodes().getLength(); i++)
		{
			Node ifcontent = e.getChildNodes().item(i);
			//skip #comments and empty lines
			if(ifcontent.getNodeName().startsWith("#"))
			{
				continue;
			}
			
			System.out.println(ifcontent.getNodeName());
			//if condition
			if(IfConditionisTrue)
			{
				switch(ifcontent.getNodeName()){
				case "goto": ;break;
				case "if": parseIf(ifcontent);break;
				case "prompt": Prompt(ifcontent);break; 
				case "reprompt": Reprompt();break;
				case "script": ;break;
				case "var": parseVar(ifcontent);;break;
				case "audio": ;break;
				}
			}
			
			switch(ifcontent.getNodeName()){
			case "elseif": 
				//check for condition first
				if(elseIfvisited ==false && IfConditionisTrue == false)
				{
					//check the condition
					String elseifcondition = ifcontent.getAttributes().getNamedItem("cond").getNodeValue();
					String elseiffirstOP = elseifcondition.split("==")[0].replace(" ", "").replace("\'","");
					String elseifsecondOP = elseifcondition.split("==")[1].replace(" ", "").replace("\'","");
					
					if(FieldHM.get(firstOP) == secondOP){ //condition satisfied
						IfConditionisTrue = true; //check condition to allow upcoming tags
						elseIfvisited = true; //set to true to avoid more elseifs and elses
					}
				};break;
				
			case "else": 
				//visit only if no elseif ever visited and make sure the condition in the original if is invalid
				if(elseIfvisited == false && IfConditionisTrue == false)
				{
					IfConditionisTrue = true; //set to true to allow upcoming tags
				};break;
			}
		}
	}
	
	
	public void parseVar(Node e)
	{System.out.println("ASDA");
		if(e.getAttributes().getNamedItem("name") != null && e.getAttributes().getNamedItem("expr") != null)
		{
			//add to Hashmap of Variables
			this.VarHM.put(e.getAttributes().getNamedItem("name").getNodeValue(), e.getAttributes().getNamedItem("expr").getNodeValue());
		}
	}
	public void parseForm(Node e)

	{
		for(int i = 0; i < e.getChildNodes().getLength(); i++)
		{
			Node formcontent = e.getChildNodes().item(i);
			//skip #comments and empty lines
			if(formcontent.getNodeName().startsWith("#"))
			{
				continue;
			}
			
			System.out.println(formcontent.getNodeName());
			
			switch(formcontent.getNodeName()){
			case "script": parseScript(formcontent);break;
			case "block": parseBlock(formcontent);break;
			case "field": parseField(formcontent);break;
			case "filled": parseFilled(formcontent);break;
		//	case "grammar": ;break; We will assume for all test cases that they are defined explicitly for the field.
		//	case "noinput": ;break; We will assume for all test cases that they are defined explicitly for the field.
		//	case "nomatch": ;break; We will assume for all test cases that they are defined explicitly for the field.
			case "var": parseVar(formcontent);break;//var/
			case "help": ;break;
			}
		}
	}
	public void parseFilled(Node e)
	{
		//check namespace
		if(e.getAttributes().getNamedItem("namespace") == null)
		{
			if(e.getParentNode().getNodeName() == "field" && this.FieldHM.get(e.getParentNode().getAttributes().getNamedItem("name").getNodeValue()) != null){
				//execute
				
			}
			if(e.getParentNode().getNodeName() == "form"){
				//execute 3ady
			}
			
			
		}else{
			if(e.getParentNode().getNodeName() == "field"){
				System.out.println("SYSTEM ERROR, CORRUPTED VXML FILE");
				System.exit(0);
			}
			if(e.getParentNode().getNodeName() == "form"){
				//has to make sure every field is assigned
				String namespacevariables = e.getAttributes().getNamedItem("namespace").getNodeValue();
				String[] fieldvars = namespacevariables.split(" ");
				
				for(int i = 0 ; i < fieldvars.length;i++)
				{
					if(FieldHM.get(fieldvars[i]) == null)
						break;
				}
				//else continue 
				
			}
		}
	}
	
	
	public void parseScript(Node e)
	{
		System.out.println(e.getTextContent());
	}
	public void parseField(Node e)
	{
		String FieldName = e.getAttributes().getNamedItem("name").getNodeValue();
		
		for(int i = 0; i < e.getChildNodes().getLength(); i++)
		{
			Node formcontent = e.getChildNodes().item(i);
			//skip #comments and empty lines
			if(formcontent.getNodeName().startsWith("#"))
			{
				continue;
			}
			
			System.out.println(formcontent.getNodeName());
			
			switch(formcontent.getNodeName()){
			case "filled": parseFilled(formcontent);break;
			case "grammar": parseGrammar(formcontent);break;
			case "noinput": ;break;
			case "nomatch": ;break;
			case "prompt": Prompt(formcontent);break;
			case "help": ;break;
			case "audio": ;break;
			}
		}
	}
	public void parseGrammar(Node e)
	{
		
	}
	public void Prompt(Node e)
	{
		//dun forget the inside tags
		String s = e.getTextContent();
		System.out.println(s);
		this.lastPrompt = s;
	}
	public void Reprompt()
	{
		System.out.println(this.lastPrompt);
	}
	
	public static void main(String[] args) {
		// file constructor
		File fXmlFile = new File("src/test2.vxml");
		// initialize the Parser class
		Parser P = new Parser(fXmlFile);

	}
}
