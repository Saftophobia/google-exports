package vxmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	HashMap<String, String> ScriptHM;
	// HashMap<String, String> FieldHM; assuming Var and field names are unique
	HashMap<String, String> VarHM;
	String lastPrompt = "";
	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = manager.getEngineByName("JavaScript");

	// constructor
	public Parser(File filename) {

		// hashmap to store Scripts
		this.ScriptHM = new HashMap<String, String>();

		this.VarHM = new HashMap<String, String>();

		// initialize the DBFactory for parsing
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			// create new documentbuilder using the factory
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// parse the file into a Document
			Document doc = dBuilder.parse(filename);
			// Puts all Text nodes in the full depth of the sub-tree underneath
			// this Node into
			// a normal form (structures between text nodes)
			// doc.getDocumentElement().normalize();

			// get Number of VXMLs Tags, if more than 1, return Error,
			NodeList vxmlNL = doc.getElementsByTagName("vxml");
			if (vxmlNL.getLength() != 1) {
				System.out.println("Error, Corrupted VXML File");
				System.exit(0);
			}

			// type cast the vxml tag into an element to access the children
			Node vxmlElement = (Node) vxmlNL.item(0);

			// Loop for all children inside VXML
			for (int i = 0; i < vxmlElement.getChildNodes().getLength(); i++) {
				// get every child
				Node e = vxmlElement.getChildNodes().item(i);
				// skip #comments and empty lines
				if (e.getNodeName().startsWith("#")) {
					continue;
				}

				System.out.println(e.getNodeName());

				switch (e.getNodeName()) {
				case "script":
					parseScript(e);
					break;
				case "form":
					this.parseForm(e);
					break;
				// case "noinput": ;break; there are no active grammars in the
				// vxml tag
				// case "nomatch": ;break; there are no active grammars in the
				// vxml tag
				case "var":
					parseVar(e);
					break;// var/
				case "help":
					;
					break;
				}

			}

		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void parseScript(Node e) {
		if (e.getAttributes().getNamedItem("src") != null) {
			try {
				ScriptHM.put(
						e.toString(),
						this.readFile(e.getAttributes().getNamedItem("src")
								.getNodeValue()));
				try {
					engine.eval(this.readFile(e.getAttributes()
							.getNamedItem("src").getNodeValue()));
				} catch (ScriptException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (DOMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			if (e.getTextContent() != null) {
				String FunctionName = e
						.getTextContent()
						.substring(e.getTextContent().indexOf("function") + 8,
								e.getTextContent().indexOf("("))
						.replace(" ", "");
				ScriptHM.put(FunctionName, e.getTextContent());
				try {
					engine.eval(e.getTextContent());
				} catch (DOMException | ScriptException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

	}

	public String parseValue(Node e) {
		String expr = e.getAttributes().getNamedItem("expr").getNodeValue();
		if (expr.endsWith(")")) // a function
		{

			// Double CalcPrice = (Double) inv.invokeFunction("price",
			// Size,Toppings,Crust,Thickness);
			String FunctionName = expr.substring(0, expr.indexOf("("));
			String[] FunctionArgument = expr.substring(expr.indexOf("("),
					expr.indexOf(")")).split(",");
			for (int i = 0; i < FunctionArgument.length; i++) {
				FunctionArgument[i] = VarHM.get(FunctionArgument[i]);
			}
			
			// Iterator it = ScriptHM.entrySet().iterator();
			// while(it.hasNext()){
			// Map.Entry pairs = (Map.Entry)it.next();
			// invoke
			// ScriptEngineManager manager = new ScriptEngineManager();
			// ScriptEngine engine = manager.getEngineByName("JavaScript");

			// eval the string
			try {
				// engine.eval(pairs.getValue().toString());

				Invocable inv = (Invocable) engine;

				String Output = (String) inv.invokeFunction(FunctionName,
						FunctionArgument);
				return Output;
			} catch (ScriptException | NoSuchMethodException e1) {
				// can't find the method
			}
			// }
		} else {
			return VarHM.get(expr);
		}
		return null;

	}

	public void parseBlock(Node e) {
		for (int i = 0; i < e.getChildNodes().getLength(); i++) {
			Node blockcontent = e.getChildNodes().item(i);
			// skip #comments and empty lines
			if (blockcontent.getNodeName().startsWith("#")) {
				continue;
			}

			System.out.println(blockcontent.getNodeName());

			switch (blockcontent.getNodeName()) {
			case "goto":
				;
				break;
			case "if":
				parseIf(blockcontent);
				break;
			case "prompt":
				this.Prompt(blockcontent);
				break;
			case "reprompt":
				this.Reprompt();
				break;
			case "script":
				this.parseScript(blockcontent);
				break;
			case "var":
				this.parseVar(blockcontent);
				break;// var/
			case "audio":
				;
				break;
			}
		}
	}

	public void parseIf(Node e) {
		boolean IfConditionisTrue = true;
		boolean elseIfvisited = false;
		// assuming If always has a valid condition
		String condition = e.getAttributes().getNamedItem("cond")
				.getNodeValue();
		String firstOP = condition.split("==")[0].replace(" ", "").replace(
				"\'", "");
		String secondOP = condition.split("==")[1].replace(" ", "").replace(
				"\'", "");

		if (VarHM.get(firstOP) != secondOP) { // not equal
			IfConditionisTrue = false;
		}

		// continue parsing
		for (int i = 0; i < e.getChildNodes().getLength(); i++) {
			Node ifcontent = e.getChildNodes().item(i);
			// skip #comments and empty lines
			if (ifcontent.getNodeName().startsWith("#")) {
				continue;
			}

			System.out.println(ifcontent.getNodeName());
			// if condition
			if (IfConditionisTrue) {
				switch (ifcontent.getNodeName()) {
				case "goto":
					;
					break;
				case "if":
					parseIf(ifcontent);
					break;
				case "prompt":
					Prompt(ifcontent);
					break;
				case "reprompt":
					Reprompt();
					break;
				case "script":
					;
					break;
				case "var":
					parseVar(ifcontent);
					;
					break;
				case "audio":
					;
					break;
				}
			}

			switch (ifcontent.getNodeName()) {
			case "elseif":
				// check for condition first
				if (elseIfvisited == false && IfConditionisTrue == false) {
					// check the condition
					String elseifcondition = ifcontent.getAttributes()
							.getNamedItem("cond").getNodeValue();
					String elseiffirstOP = elseifcondition.split("==")[0]
							.replace(" ", "").replace("\'", "");
					String elseifsecondOP = elseifcondition.split("==")[1]
							.replace(" ", "").replace("\'", "");

					if (VarHM.get(firstOP) == secondOP) { // condition satisfied
						IfConditionisTrue = true; // check condition to allow
													// upcoming tags
						elseIfvisited = true; // set to true to avoid more
												// elseifs and elses
					}
				}
				;
				break;

			case "else":
				// visit only if no elseif ever visited and make sure the
				// condition in the original if is invalid
				if (elseIfvisited == false && IfConditionisTrue == false) {
					IfConditionisTrue = true; // set to true to allow upcoming
												// tags
				}
				;
				break;
			}
		}
	}

	public void parseVar(Node e) {
		if (e.getAttributes().getNamedItem("name") != null
				&& e.getAttributes().getNamedItem("expr") != null) {
			// add to Hashmap of Variables
			this.VarHM.put(e.getAttributes().getNamedItem("name")
					.getNodeValue(), e.getAttributes().getNamedItem("expr")
					.getNodeValue());
		}
	}

	public void parseForm(Node e)

	{
		for (int i = 0; i < e.getChildNodes().getLength(); i++) {
			Node formcontent = e.getChildNodes().item(i);
			// skip #comments and empty lines
			if (formcontent.getNodeName().startsWith("#")) {
				continue;
			}

			System.out.println(formcontent.getNodeName());

			switch (formcontent.getNodeName()) {
			case "script":
				parseScript(formcontent);
				break;
			case "block":
				parseBlock(formcontent);
				break;
			case "field":
				parseField(formcontent);
				break;
			case "filled":
				parseFilled(formcontent);
				break;
			// case "grammar": ;break; We will assume for all test cases that
			// they are defined explicitly for the field.
			// case "noinput": ;break; We will assume for all test cases that
			// they are defined explicitly for the field.
			// case "nomatch": ;break; We will assume for all test cases that
			// they are defined explicitly for the field.
			case "var":
				parseVar(formcontent);
				break;// var/
			case "help":
				;
				break;
			}
		}
	}

	public void parseFilled(Node e) {
		// check namespace
		if (e.getAttributes().getNamedItem("namelist") == null) {
			if (e.getParentNode().getNodeName() == "field"
					&& this.VarHM.get(e.getParentNode().getAttributes()
							.getNamedItem("name").getNodeValue()) != null) {
				// execute
				for (int i = 0; i < e.getChildNodes().getLength(); i++) {
					Node formcontent = e.getChildNodes().item(i);
					// skip #comments and empty lines
					if (formcontent.getNodeName().startsWith("#")) {
						continue;
					}

					System.out.println(formcontent.getNodeName());

					switch (formcontent.getNodeName()) {
					case "audio":
						;
						break;
					case "goto":
						;
						break;
					case "if":
						parseIf(formcontent);
						break;
					case "prompt":
						Prompt(formcontent);
						break;
					case "reprompt":
						Reprompt();
						break;
					case "script":
						parseScript(formcontent);

						break;
					case "value":
						parseValue(formcontent);
						break;
					case "var":
						parseValue(formcontent);
						break;
					}
				}
			}

		} else {
			if (e.getParentNode().getNodeName() == "field") {
				System.out.println("SYSTEM ERROR, CORRUPTED VXML FILE");
				System.exit(0);
			}
			if (e.getParentNode().getNodeName() == "form") {
				// has to make sure every field is assigned
				String namespacevariables = e.getAttributes()
						.getNamedItem("namespace").getNodeValue();
				String[] fieldvars = namespacevariables.split(" ");

				if (e.getAttributes().getNamedItem("mode").getNodeValue() == "all"
						|| e.getAttributes().getNamedItem("mode")
								.getNodeValue() == null) {
					for (int i = 0; i < fieldvars.length; i++) {
						if (VarHM.get(fieldvars[i]) == null)
							return;
					}
				} else {
					if (e.getAttributes().getNamedItem("mode").getNodeValue() == "any") {
						boolean found = false;
						for (int i = 0; i < fieldvars.length; i++) {
							if (VarHM.get(fieldvars[i]) != null)
								found = true;
						}
						if (!found) {
							return;
						}
					}
				}

				// else continue the form filled
				for (int i = 0; i < e.getChildNodes().getLength(); i++) {
					Node formcontent = e.getChildNodes().item(i);
					// skip #comments and empty lines
					if (formcontent.getNodeName().startsWith("#")) {
						continue;
					}

					System.out.println(formcontent.getNodeName());

					switch (formcontent.getNodeName()) {
					case "audio":
						;
						break;
					case "goto":
						;
						break;
					case "if":
						parseIf(formcontent);
						break;
					case "prompt":
						Prompt(formcontent);
						break;
					case "reprompt":
						Reprompt();
						break;
					case "script":
						parseScript(formcontent);
						break;
					case "value":
						parseValue(formcontent);
						break;
					case "var":
						parseValue(formcontent);
						break;
					}
				}
			}
		}
	}

	public void parseField(Node e) {
		String FieldName = e.getAttributes().getNamedItem("name")
				.getNodeValue();

		for (int i = 0; i < e.getChildNodes().getLength(); i++) {
			Node formcontent = e.getChildNodes().item(i);
			// skip #comments and empty lines
			if (formcontent.getNodeName().startsWith("#")) {
				continue;
			}

			System.out.println(formcontent.getNodeName());

			switch (formcontent.getNodeName()) {
			case "filled":
				parseFilled(formcontent);
				break;
			case "grammar":
				parseGrammar(formcontent);
				break;
			case "noinput":
				;
				break;
			case "nomatch":
				;
				break;
			case "prompt":
				Prompt(formcontent);
				break;
			case "help":
				;
				break;
			case "audio":
				;
				break;
			}
		}
	}

	public void parseGrammar(Node e) {

	}

	public void Prompt(Node e) {
		// dun forget the inside tags
		String s = "";
		for(int i = 0; i < e.getChildNodes().getLength();i++)
		{
			Node iterated = e.getChildNodes().item(i);
			if(iterated.getNodeName() == "#text")
			{
				s += iterated.getNodeValue();
			}else{
				if(iterated.getNodeName() == "value")
				{
					s += parseValue(iterated);
				}
			}
		}
		
		System.out.println(s);
		this.lastPrompt = s;
	}

	public void Reprompt() {
		System.out.println(this.lastPrompt);
		//wait for input
	}

	public String readFile(String pathname) throws IOException {

		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");

		try {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}

	public static void main(String[] args) {
		// file constructor
		File fXmlFile = new File("src/test2.vxml");
		// initialize the Parser class
		Parser P = new Parser(fXmlFile);

	}
}
