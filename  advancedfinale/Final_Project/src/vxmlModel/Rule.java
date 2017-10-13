package vxmlModel;

import java.util.ArrayList;

import util.StateVariables;

public class Rule extends TagHolder {

	String id;
	String scope;

	ArrayList<Tag> children;

	public Rule(String id, String scope) {
		super();
		this.id = id;
		this.children = new ArrayList<Tag>();
		this.scope = scope;
	}

	public String getId() {
		return id;
	}

	public String getScope() {
		return scope;
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
		for (Tag t : this.children) {
			try {
				if ((boolean) t.eval(o)) {
					return true;
				}
			} catch (Exception e) {
				if ((boolean) t.eval(o)) {
					return true;
				}
			}
		}
		return false;
	}

	public ArrayList<Tag> getChildren() {
		return children;
	}

}
