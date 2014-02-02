package vxmlModel;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VxmlParser {

	// HashMap<String, String> ScriptHashMap;
	// HashMap<String, String> VariableHashMap;
	// ScriptEngineManager manager = new ScriptEngineManager();
	// ScriptEngine engine = manager.getEngineByName("JavaScript");
	// String lastPrompt = "";

	public VxmlParser(File filename) {
		// this.ScriptHashMap = new HashMap<String, String>();
		// this.VariableHashMap = new HashMap<String, String>();
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
					vxmlclass.addChild(parseScript(e));

					break;
				case "form":
					vxmlclass.addChild(parseForm(e));
					break;
				case "noinput":
					vxmlclass.addChild(parseNoInput(e));

					break;
				case "nomatch":
					vxmlclass.addChild(parseNoMatch(e));

					break;
				case "var":
					vxmlclass.addChild(parseVar(e));
					break;
				case "help":
					vxmlclass.addChild(parseHelp(e));

					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Grammar parseGrammar(Node nodeE) {
		Grammar grammarClass = new Grammar(null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "getchhint":
				grammarClass.fetchHint = attribute.getNodeValue();
				break;
			case "fetchtimeout":
				grammarClass.fetchtimeout = attribute.getNodeValue();
				break;
			case "src":
				grammarClass.src = attribute.getNodeValue();
				break;
			case "srcexpr":
				grammarClass.srcexpr = attribute.getNodeValue();
				break;
			case "mode":
				grammarClass.mode = attribute.getNodeValue();
				break;
			case "scope":
				grammarClass.scope = attribute.getNodeValue();
				break;
			case "tagformat":
				grammarClass.tagFormat = attribute.getNodeValue();
				break;
			case "type":
				grammarClass.type = attribute.getNodeValue();
				break;
			case "version":
				grammarClass.version = attribute.getNodeValue();
				break;
			case "voxeo:useuri":
				grammarClass.voxeo_useuri = attribute.getNodeValue();
				break;
			case "weight":
				grammarClass.weight = attribute.getNodeValue();
				break;
			case "xml:base":
				grammarClass.xml_base = attribute.getNodeValue();
				break;
			case "xml:lang":
				grammarClass.xml_lang = attribute.getNodeValue();
				break;
			case "xmlns":
				grammarClass.xmlns = attribute.getNodeValue();
				break;
			case "root":
				grammarClass.root = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "rule":
				grammarClass.addChild(parseRule(e));
				break;
			}
		}
		return grammarClass;
	}

	public Rule parseRule(Node nodeE) {
		Rule ruleClass = new Rule(null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "id":
				ruleClass.id = attribute.getNodeValue();
				break;
			case "scope":
				ruleClass.scope = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "item":
				ruleClass.addChild(parseItem(e));
				break;
			case "one-of":
				ruleClass.addChild(parseOneOf(e)); // errored
				break;
			}
		}
		return ruleClass;
	}

	public Item parseItem(Node nodeE) {
		Item itemClass = new Item(null, null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "weight":
				itemClass.weight = attribute.getNodeValue();
				break;
			case "prob":
				itemClass.prob = attribute.getNodeValue();
				break;
			case "repeat":
				itemClass.repeat = attribute.getNodeValue();
				break;
			}

		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "item":
				itemClass.addChild(parseItem(e));
				break;
			case "one-of":
				itemClass.addChild(parseOneOf(e));
				break;
			}
		}
		return itemClass;
	}

	public OneOf parseOneOf(Node nodeE) {
		OneOf oneofClass = new OneOf();

		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "item":
				oneofClass.addChild(parseItem(e));
				break;
			}
		}
		return oneofClass;
	}

	public Help parseHelp(Node nodeE) {
		Help helpClass = new Help(null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "cond":
				helpClass.cond = attribute.getNodeValue();
				break;
			case "count":
				helpClass.count = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "audio":
				helpClass.addChild(parseAudio(e));

				break;
			case "goto":
				helpClass.addChild(parseGoto(e));
				break;
			case "if":
				helpClass.addChild(parseIf(e));

				break;
			case "prompt":
				helpClass.addChild(parsePrompt(e));

				break;
			case "reprompt":
				helpClass.addChild(parseReprompt(e));

				break;
			case "script":
				helpClass.addChild(parseScript(e));
				break;
			case "value":
				helpClass.addChild(parseValue(e));

				break;
			case "var":
				helpClass.addChild(parseVar(e));

				break;
			}
		}
		return helpClass;
	}

	public Var parseVar(Node nodeE) {
		Var varClass = new Var(null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "expr":
				varClass.expr = attribute.getNodeValue();
				break;
			case "name":
				varClass.name = attribute.getNodeValue();
				break;
			}
		}
		return varClass;
	}

	public NoInput parseNoInput(Node nodeE) {
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
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "audio":
				noInputClass.addChild(parseAudio(e));
				break;
			case "goto":
				noInputClass.addChild(parseGoto(e));
				break;
			case "if":
				noInputClass.addChild(parseIf(e));
				;
				break;
			case "reprompt":
				noInputClass.addChild(parseReprompt(e));
				;
				break;
			case "script":
				noInputClass.addChild(parseScript(e));
				break;
			}
		}
		return noInputClass;
	}

	public Goto parseGoto(Node nodeE) {
		Goto gotoClass = new Goto(null, null, null, null, null, null, null,
				null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "expr":
				gotoClass.expr = attribute.getNodeValue();
				break;
			case "expritem":
				gotoClass.exprItem = attribute.getNodeValue();
				break;
			case "fetchaudio":
				gotoClass.fetchAudio = attribute.getNodeValue();
				break;
			case "fetchhint":
				gotoClass.fetchHint = attribute.getNodeValue();
				break;
			case "fetchtimeout":
				gotoClass.fetchTimeOut = attribute.getNodeValue();
				break;
			case "maxage":
				gotoClass.maxAge = attribute.getNodeValue();
				break;
			case "maxstale":
				gotoClass.maxAge = attribute.getNodeValue();
				break;
			case "next":
				gotoClass.next = attribute.getNodeValue();
				break;
			case "nextitem":
				gotoClass.nextItem = attribute.getNodeValue();
				break;
			}
		}
		return gotoClass;
	}

	public NoMatch parseNoMatch(Node nodeE) {
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
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "audio":
				noMatchClass.addChild(parseAudio(e));

				break;
			case "goto":
				noMatchClass.addChild(parseGoto(e));
				break;
			case "if":
				noMatchClass.addChild(parseIf(e));
				;
				break;
			case "prompt":
				noMatchClass.addChild(parsePrompt(e));
				;
				break;
			case "reprompt":
				noMatchClass.addChild(parseReprompt(e));
				;
				break;
			case "script":
				noMatchClass.addChild(parseScript(e));
				break;
			case "value":
				noMatchClass.addChild(parseValue(e));
				break;
			case "var":
				noMatchClass.addChild(parseVar(e));
				break;
			}
		}
		return noMatchClass;
	}

	public Prompt parsePrompt(Node NodeE) {
		Prompt promptClass = new Prompt(null, null, null, null, null, null,
				null);
		for (int i = 0; i < NodeE.getAttributes().getLength(); i++) {
			Node attribute = NodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "bargein":
				promptClass.bargein = attribute.getNodeValue();
				break;
			case "bargeintype":
				promptClass.bargeinType = attribute.getNodeValue();
				break;
			case "cond":
				promptClass.condition = attribute.getNodeValue();
				break;
			case "count":
				promptClass.count = attribute.getNodeValue();
				break;
			case "timeout":
				promptClass.timeout = attribute.getNodeValue();
				break;
			case "xml:lang":
				promptClass.xml_lang = attribute.getNodeValue();
				break;
			}
		}
		promptClass.data = NodeE;
		return promptClass;
	}

	public Reprompt parseReprompt(Node NodeE) {
		return new Reprompt();
	}

	public Script parseScript(Node e) {
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

	public Form parseForm(Node nodeE) {
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
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "block":
				formclass.addChild(parseBlock(e));

				break;
			case "field":
				formclass.addChild(parseField(e));
				break;
			case "filled":
				formclass.addChild(parseFilled(e));

				break;
			case "grammar":
				formclass.addChild(parseGrammar(e));

				break;
			case "help":
				formclass.addChild(parseHelp(e));
				break;
			case "noinput":
				formclass.addChild(parseNoInput(e));

				break;
			case "nomatch":
				formclass.addChild(parseNoMatch(e));

				break;
			case "script":
				formclass.addChild(parseScript(e));

				break;
			case "var":
				formclass.addChild(parseVar(e));

				break;
			}
		}
		return formclass;
	}

	public Field parseField(Node nodeE) {
		Field fieldClass = new Field(null, null, null, null, null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "cond":
				fieldClass.condition = attribute.getNodeValue();
				break;
			case "expr":
				fieldClass.expr = attribute.getNodeValue();
				break;
			case "modal":
				fieldClass.modal = attribute.getNodeValue();
				break;
			case "name":
				fieldClass.name = attribute.getNodeValue();
				break;
			case "slot":
				fieldClass.slot = attribute.getNodeValue();
				break;
			case "type":
				fieldClass.type = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "audio":
				fieldClass.addChild(parseAudio(e));

				break;

			case "filled":
				fieldClass.addChild(parseFilled(e));

				break;
			case "grammar":
				fieldClass.addChild(parseGrammar(e));

				break;
			case "help":
				fieldClass.addChild(parseHelp(e));
				break;
			case "noinput":
				fieldClass.addChild(parseNoInput(e));

				break;
			case "nomatch":
				fieldClass.addChild(parseNoMatch(e));

				break;
			case "prompt":
				fieldClass.addChild(parseScript(e));

				break;
			case "value":
				fieldClass.addChild(parseValue(e));

				break;
			}
		}
		return fieldClass;
	}

	public Filled parseFilled(Node nodeE) {
		Filled filledClass = new Filled(null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "mode":
				filledClass.mode = attribute.getNodeValue();
				break;
			case "namelist":
				filledClass.nameList = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "audio":
				filledClass.addChild(parseAudio(e));

				break;
			case "goto":
				filledClass.addChild(parseGoto(e));
				break;
			case "if":
				filledClass.addChild(parseIf(e));

				break;
			case "reprompt":
				filledClass.addChild(parseReprompt(e));

				break;
			case "prompt":
				filledClass.addChild(parsePrompt(e));

				break;
			case "value":
				filledClass.addChild(parseValue(e));

				break;
			case "var":
				filledClass.addChild(parseVar(e));

				break;
			case "script":
				filledClass.addChild(parseScript(e));

				break;
			}
		}
		return filledClass;
	}

	public Audio parseAudio(Node nodeE) {
		Audio audioclass = new Audio(null, null, null, null, null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "expr":
				audioclass.expr = attribute.getNodeValue();
				break;
			case "fetchhint":
				audioclass.fetchHint = attribute.getNodeValue();
				break;
			case "fetchtimeout":
				audioclass.fetchTimeOut = attribute.getNodeValue();
				break;
			case "maxage":
				audioclass.maxAge = attribute.getNodeValue();
				break;
			case "maxstale":
				audioclass.maxStale = attribute.getNodeValue();
				break;
			case "src":
				audioclass.src = attribute.getNodeValue();
				break;
			}
		}

		return audioclass;
	}

	public Block parseBlock(Node nodeE) {
		Block blockClass = new Block(null, null, null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "cond":
				blockClass.cond = attribute.getNodeValue();
				break;
			case "expr":
				blockClass.expr = attribute.getNodeValue();
				break;
			case "name":
				blockClass.name = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "audio":
				blockClass.addChild(parseAudio(e));
				break;
			case "goto":
				blockClass.addChild(parseGoto(e));
				break;
			case "if":
				blockClass.addChild(parseIf(e));

				break;
			case "prompt":
				blockClass.addChild(parsePrompt(e));

				break;
			case "reprompt":
				blockClass.addChild(parseReprompt(e));
				break;
			case "script":
				blockClass.addChild(parseScript(e));

				break;
			case "value":
				blockClass.addChild(parseValue(e));

				break;
			case "var":
				blockClass.addChild(parseVar(e));

				break;
			}
		}
		return blockClass;
	}

	public If parseIf(Node nodeE) {
		If ifClass = new If(null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "cond":
				ifClass.cond = attribute.getNodeValue();
				break;
			}
		}
		for (int i = 0; i < nodeE.getChildNodes().getLength(); i++) {
			Node e = nodeE.getChildNodes().item(i);
			if (e.getNodeName().startsWith("#")) {
				continue;
			}
			System.out.println(e.getNodeName());
			switch (e.getNodeName()) {
			case "audio":
				ifClass.addChild(parseAudio(e));

				break;
			case "else":
				ifClass.addChild(parseElse(e));
				break;
			case "elseif":
				ifClass.addChild(parseElseIf(e));

				break;
			case "goto":
				ifClass.addChild(parseGoto(e));

				break;
			case "if":
				ifClass.addChild(parseIf(e));
				break;
			case "prompt":
				ifClass.addChild(parsePrompt(e));

				break;
			case "reprompt":
				ifClass.addChild(parseReprompt(e));

				break;
			case "script":
				ifClass.addChild(parseScript(e));

				break;
			case "var":
				ifClass.addChild(parseVar(e));

				break;
			case "value":
				ifClass.addChild(parseValue(e));

				break;
			}
		}
		return ifClass;
	}

	public Value parseValue(Node nodeE) {
		Value valueClass = new Value(null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "expr":
				valueClass.expr = attribute.getNodeValue();
				break;
			}
		}
		return valueClass;
	}

	public Elseif parseElseIf(Node nodeE) {
		Elseif elseIf = new Elseif(null);
		for (int i = 0; i < nodeE.getAttributes().getLength(); i++) {
			Node attribute = nodeE.getAttributes().item(i);
			switch (attribute.getNodeName()) {
			case "cond":
				elseIf.cond = attribute.getNodeValue();
				break;
			}
		}
		return elseIf;
	}

	public Else parseElse(Node nodeE) {
		return new Else();
	}

	public static void main(String[] args) {

		new VxmlParser(new File("src/test.vxml"));

	}
}
