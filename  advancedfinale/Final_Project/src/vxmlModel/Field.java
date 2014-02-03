package vxmlModel;

import java.util.ArrayList;

import util.FreeTTSListener;
import util.StateVariables;

public class Field extends TagHolder {

	String condition;
	String expr;
	String modal;
	String name;
	String type;
	String slot;
	ArrayList<Tag> children;

	public Field(String condition, String expr, String modal, String name,
			String type, String slot) {
		super();
		this.condition = condition;
		this.expr = expr;
		this.modal = modal;
		this.name = name;
		this.type = type;
		this.slot = slot;
		this.children = new ArrayList<Tag>();
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

	public String getCondition() {
		return condition;
	}

	public String getExpr() {
		return expr;
	}

	public String getModal() {
		return modal;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getSlot() {
		return slot;
	}

	public ArrayList<Tag> getChildren() {
		return children;
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
		if (condition != null) {
			if (condition.contains("==")) {
				String firstOP = condition.split("==")[0].replace(" ", "")
						.replace("\'", "");
				String secondOP = condition.split("==")[1].replace(" ", "")
						.replace("\'", "");

				if (o.VariableHashMap.get(firstOP) != secondOP) { // not
																			// equal
					return null;
				}
			} else {
				if (condition.contains("!=")) {
					String firstOP = condition.split("!=")[0].replace(" ", "")
							.replace("\'", "");
					String secondOP = condition.split("!=")[1].replace(" ", "")
							.replace("\'", "");

					if (o.VariableHashMap.get(firstOP) == secondOP) { // not
																				// equal
						return null;
					}
				}
			}
		}

		if (expr != null && name != null) {
			if (!expr.equalsIgnoreCase("undefined")) {
				o.VariableHashMap.put(name, expr);
				return null;
			}
		}
		for (Tag t : children) {
			if(t instanceof Value)
			{
				for (FreeTTSListener listener : o.Listerners) {
					listener.Say((String)t.eval(o));
				}
			}else{
			t.eval(o);
			}
		}
		return null;
	}

}
