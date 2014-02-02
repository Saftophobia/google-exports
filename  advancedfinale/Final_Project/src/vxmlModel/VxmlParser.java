package vxmlModel;

import java.io.File;
import java.util.HashMap;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VxmlParser {

	HashMap<String, String> ScriptHashMap;
	HashMap<String, String> VariableHashMap;
	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = manager.getEngineByName("JavaScript");
	String lastPrompt = "";

	public VxmlParser(File filename) {
		this.ScriptHashMap = new HashMap<String, String>();
		this.VariableHashMap = new HashMap<String, String>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(filename);
			NodeList vxmlNL = doc.getElementsByTagName("vxml");
			Node vxmlElement = (Node) vxmlNL.item(0);
			Vxml vxmlclass= new Vxml(null, null, null, null, null, null);
			for(int i=0;i<vxmlElement.getAttributes().getLength();i++)
			{
				Node attribute = vxmlElement.getAttributes().item(i);
				switch(attribute.getNodeName()){
				case "application":vxmlclass.application = attribute.getNodeValue();break;
				case "version":vxmlclass.version = attribute.getNodeValue();break;
				case "xml:base":vxmlclass.xml_base = attribute.getNodeValue();break;
				case "xml:lang":vxmlclass.xml_lang = attribute.getNodeValue();break;
				case "xmlns":vxmlclass.xmlns = attribute.getNodeValue();break;
				case "xmlns:voxeo":vxmlclass.xmlns_voxeo = attribute.getNodeValue();break;
				}
				
			}
			for (int i = 0; i < vxmlElement.getChildNodes().getLength(); i++) {
				Node e = vxmlElement.getChildNodes().item(i);
				// skip #comments and empty lines
				if (e.getNodeName().startsWith("#")) {
					continue;
				}
				System.out.println(e.getNodeName());
				switch (e.getNodeName()) {
				case "script": vxmlclass.addChild(ParseScript(e));
				
					break;
				case "form":vxmlclass.addChild(ParseForm(e));
					break;
				case "noinput":vxmlclass.addChild(ParseNoInput(e));
					;
					break;
				case "nomatch":vxmlclass.addChild(ParseNoMatch(e));
					;
					break;
				case "var":vxmlclass.addChild(ParseVar(e));
					break;
				case "help":vxmlclass.addChild(ParseHelp(e));
					;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Script ParseScript(Node e) {
		Script scriptClass= new Script(null, null, null, null, null, null,null,null);
		for(int i=0;i<e.getAttributes().getLength();i++)
		{
			Node attribute = e.getAttributes().item(i);
			switch(attribute.getNodeName()){
			case "charset":scriptClass.charSet = attribute.getNodeValue();break;
			case "fetchint":scriptClass.fetchHint = attribute.getNodeValue();break;
			case "fetchtimeout":scriptClass.fetchTimeOut = attribute.getNodeValue();break;
			case "maxage":scriptClass.maxAge = attribute.getNodeValue();break;
			case "maxstale":scriptClass.maxStale = attribute.getNodeValue();break;
			case "src":scriptClass.src = attribute.getNodeValue();break;
			case "srcexpr":scriptClass.srcExpr = attribute.getNodeValue();break;
			}
		}
		if(scriptClass.src == null)
		{
			scriptClass.ScriptContent = e.getTextContent();
		}	
		return scriptClass;
	}
	
	
	

	public static void main(String[] args) {

	}
}
