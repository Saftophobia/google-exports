package vxmlModel;

import java.util.ArrayList;

import org.w3c.dom.Node;

import util.FreeTTSListener;
import util.StateVariables;

public class Prompt extends TagHolder {

	String bargein;
	String bargeinType;
	String condition;
	String count;
	String timeout;
	String xml_lang;
	Node data;
	ArrayList<Tag> children;

	public Prompt(String bargein, String bargeinType, String condition,
			String count, String timeout, String xml_lang, Node data) {
		super();
		this.bargein = "true";
		this.bargeinType = "speech";
		this.condition = condition;
		this.count = count;
		this.timeout = "5";
		this.xml_lang = xml_lang;
		this.data = data;
		children = new ArrayList<Tag>();
		identifier = 4;
	}

	public Node getData() {
		return data;
	}

	public String getTimeOut() {
		return timeout;
	}

	public String getBargein() {
		return bargein;
	}

	public String GetBargeinTsype() {
		return bargeinType;
	}

	public String GetCondition() {
		return condition;

	}

	public String GetCount() {
		return count;
	}

	public void addChild(Tag child) {
		child.parent = this;
		children.add(child);
	}

	public Tag getChild() {
		return children.get(parsingIndex++);
	}

	public void updateParsingIndex(int i) {
		parsingIndex = i;
	}

	public ArrayList<Tag> getTagsByType(int identifier) {
		ArrayList<Tag> output = new ArrayList<Tag>();
		for (Tag child : children) {
			if (child.identifier == identifier) {
				output.add(child);
			}
		}
		return output;
	}

	@Override
	public Object eval(Object o) {

		if (condition != null) {
			if (condition.contains("==")) {
				String firstOP = condition.split("==")[0].replace(" ", "")
						.replace("\'", "");
				String secondOP = condition.split("==")[1].replace(" ", "")
						.replace("\'", "");

				if (!((StateVariables) o).VariableHashMap.get(firstOP).equals(
						secondOP)) { // not
					// equal
					return null;
				}
			} else {
				if (condition.contains("!=")) {
					String firstOP = condition.split("!=")[0].replace(" ", "")
							.replace("\'", "");
					String secondOP = condition.split("!=")[1].replace(" ", "")
							.replace("\'", "");

					if (((StateVariables) o).VariableHashMap.get(firstOP)
							.equals(secondOP)) { // not
						// equal
						return null;
					}
				}
			}
		}

		String s = "";
		for (int i = 0; i < data.getChildNodes().getLength(); i++) {
			Node iterated = data.getChildNodes().item(i);
			if (iterated.getNodeName() == "#text") {
				s += iterated.getNodeValue();
			} else {
				if (iterated.getNodeName() == "value") {
					Value v = new Value(null);
					for (int z = 0; z < iterated.getAttributes().getLength(); z++) {
						Node Attribute = iterated.getAttributes().item(z);
						if (Attribute.getNodeName() == "expr") {
							v.expr = Attribute.getNodeValue();
						}

					}
					s += (String) v.eval(o);
				}
			}
		}

		for (FreeTTSListener sListener : ((StateVariables) o).Listerners) {
			sListener.Say(s);
		}

		((StateVariables) o).LastPrompt = this;

		return data;
	}

	public ArrayList<Tag> getChildren() {
		return children;
	}
}
