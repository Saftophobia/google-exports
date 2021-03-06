package vxmlModel;

import java.util.ArrayList;

import util.FreeTTSListener;
import util.StateVariables;

public class Block extends TagHolder {
	String name;
	String expr;
	String cond;
	String blockContent;
	ArrayList<Tag> children;
	boolean alreadyVisited;

	public Block(String name, String expr, String cond, String blockContent) {
		this.name = name;
		this.expr = expr;
		this.cond = cond;
		this.blockContent = blockContent;
		children = new ArrayList<Tag>();
		identifier = 3;

	}

	public String GetCondition() {
		return cond;

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
		alreadyVisited = true;

		if (cond != null) {
			if (cond.contains("==")) {
				String firstOP = cond.split("==")[0].replace(" ", "").replace(
						"\'", "");
				String secondOP = cond.split("==")[1].replace(" ", "").replace(
						"\'", "");
				if(((StateVariables) o).VariableHashMap.get(firstOP) == null){
					return null;
				}
				if (!((StateVariables) o).VariableHashMap.get(firstOP).equals(
						secondOP)) { // not
					// equal
					return null;
				}
			} else {
				if (cond.contains("!=")) {
					String firstOP = cond.split("!=")[0].replace(" ", "")
							.replace("\'", "");
					String secondOP = cond.split("!=")[1].replace(" ", "")
							.replace("\'", "");
					if(((StateVariables) o).VariableHashMap.get(firstOP) == null){
						return null;
					}
					if (((StateVariables) o).VariableHashMap.get(firstOP)
							.equals(secondOP)) { // not
						// equal
						return null;
					}
				}
			}
		}

		if (expr != null && name != null) {
			if (!expr.equalsIgnoreCase("undefined")) {
				((StateVariables) o).VariableHashMap.put(name, expr);
				return null;
			}
		}
		for (Tag t : children) {
			if (t instanceof Value) {
				for (FreeTTSListener listener : ((StateVariables) o).Listerners) {
					listener.Say((String) t.eval(o));
				}
			} else {
				t.eval(o);
			}
		}

		return null;
	}
	
	public ArrayList<Tag> getChildren() {
		return children;
	}
}
