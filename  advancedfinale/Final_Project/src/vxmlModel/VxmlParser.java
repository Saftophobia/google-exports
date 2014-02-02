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
			Vxml vxmlclass = new Vxml(null, null, null, null, null, null);
			for (int i = 0; i < vxmlElement.getAttributes().getLength(); i++) {
				Node attribute = vxmlElement.getAttributes().item(i);
				switch (attribute.getNodeName()) {
				case "application":
					vxmlclass.application = attribute.getNodeValue();
					break;
				case "version":
					vxmlclass.version = attribute.getNodeValue();
					break;
				case "xml:base":
					vxmlclass.xml_base = attribute.getNodeValue();
					break;
				case "xml:lang":
					vxmlclass.xml_lang = attribute.getNodeValue();
					break;
				case "xmlns":
					vxmlclass.xmlns = attribute.getNodeValue();
					break;
				case "xmlns:voxeo":
					vxmlclass.xmlns_voxeo = attribute.getNodeValue();
					break;
				}

			}
			for (int i = 0; i < vxmlElement.getChildNodes().getLength(); i++) {
				Node e = vxmlElement.getChildNodes().item(i);
				if (e.getNodeName().startsWith("#")) {
					continue;
				}
				System.out.println(e.getNodeName());
				switch (e.getNodeName()) {
				case "script":
					vxmlclass.addChild(ParseScript(e));

					break;
				case "form":
					vxmlclass.addChild(ParseForm(e));
					break;
				case "noinput":
					vxmlclass.addChild(ParseNoInput(e));
					;
					break;
				case "nomatch":
					vxmlclass.addChild(ParseNoMatch(e));
					;
					break;
				case "var":
					vxmlclass.addChild(ParseVar(e));
					break;
				case "help":
					vxmlclass.addChild(ParseHelp(e));
					;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Var
	public NoInput ParseNoInput(Node nodeE) {
		NoInput noInputClass = new NoInput(null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "cond":
				noInputClass.cond = attribute.getNodeValue();
				break;
			case "count":
				noInputClass.count = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = e.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "audio":
				noInputClass.addChild(ParseAudio(e));

				break;
			case "goto":
				noInputClass.addChild(ParseGoto(e));
				break;
			case "if":
				noInputClass.addChild(ParseIf(e));
				;
				break;
			case "reprompt":
				noInputClass.addChild(ParseReprompt(e));
				;
				break;
			case "script":
				noInputClass.addChild(ParseScript(e));
				break;
			}
		}
		return noInputClass;
	}

	public NoMatch ParseNoMatch(Node nodeE)
	{
		NoMatch noMatchClass = new NoMatch(null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "cond":
				noMatchClass.cond = attribute.getNodeValue();
				break;
			case "count":
				noMatchClass.count = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = e.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "audio":
				noMatchClass.addChild(ParseAudio(e));

				break;
			case "goto":
				noMatchClass.addChild(ParseGoto(e));
				break;
			case "if":
				noMatchClass.addChild(ParseIf(e));
				;
				break;
			case "prompt":
				noMatchClass.addChild(ParsePrompt(e));
				;
				break;
			case "reprompt":
				noMatchClass.addChild(ParseReprompt(e));
				;
				break;
			case "script":
				noMatchClass.addChild(ParseScript(e));
				break;
			case "value":
				noMatchClass.addChild(ParseValue(e));
				break;
			case "var":
				noMatchClass.addChild(ParseVar(e));
				break;
			}
		}
			return noMatchClass;
	}
	
	public Script ParseScript(Node e) {
		Script scriptClass = new Script(null, null, null, null, null, null,
				null, null);
		for (int i = 0; i < e.getAttributes().getLength(); i++) {
			Node attribute = e.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "charset":
				scriptClass.charSet = attribute.getNodeValue();
				break;
			case "fetchint":
				scriptClass.fetchHint = attribute.getNodeValue();
				break;
			case "fetchtimeout":
				scriptClass.fetchTimeOut = attribute.getNodeValue();
				break;
			case "maxage":
				scriptClass.maxAge = attribute.getNodeValue();
				break;
			case "maxstale":
				scriptClass.maxStale = attribute.getNodeValue();
				break;
			case "src":
				scriptClass.src = attribute.getNodeValue();
				break;
			case "srcexpr":
				scriptClass.srcExpr = attribute.getNodeValue();
				break;
			}
		}
		if (scriptClass.src == null) {
			scriptClass.ScriptContent = e.getTextContent();
		}
		return scriptClass;
	}

	public Form ParseForm(Node nodeE) {
		Form formclass = new Form(null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "id":
				formclass.id = attribute.getNodeValue();
				break;
			case "scope":
				formclass.scope = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = e.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "block":
				formclass.addChild(ParseBlock(e));

				break;
			case "field":
				formclass.addChild(ParseField(e));
				break;
			case "filled":
				formclass.addChild(ParseFilled(e));
				;
				break;
			case "grammar":
				formclass.addChild(ParseGrammar(e));
				;
				break;
			case "help":
				formclass.addChild(ParseHelp(e));
				break;
			case "noinput":
				formclass.addChild(ParseNoInput(e));
				;
				break;
			case "nomatch":
				formclass.addChild(ParseNoMatch(e));
				;
				break;
			case "script":
				formclass.addChild(ParseScript(e));
				;
				break;
			case "var":
				formclass.addChild(ParseVar(e));
				;
				break;
			}
		}
		return formclass;
	}

	public static void main(String[] args) {

	}
}
