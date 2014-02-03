package vxmlModel;

import java.util.ArrayList;

import util.StateVariables;

public class Block extends TagHolder {
	String name;
	String expr;
	String cond;
	ArrayList<Tag> children;

	public Block(String name, String expr, String cond) {
		this.name = name;
		this.expr = expr;
		this.cond = cond;
		children = new ArrayList<Tag>();

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
	public Object eval(StateVariables o) {
		// TODO Auto-generated method stub
		if (cond != null) {
			String firstOP = cond.split("==")[0].replace(" ", "").replace("\'",
					"");
			String secondOP = cond.split("==")[1].replace(" ", "").replace(
					"\'", "");

			if (o.VariableHashMap.get(firstOP) != secondOP) { // not
																		// equal
				return null;
			}
		}
		if (expr != null && name != null) {
			if (!expr.equalsIgnoreCase("undefined")) {
				o.VariableHashMap.put(name, expr);
				return null;
			}
		}
		for (Tag t : children) {
			t.eval(o);
		}

		return null;
	}
}
