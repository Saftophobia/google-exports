package vxmlModel;

import java.util.ArrayList;

import util.StateVariables;

public class NoInput extends TagHolder {

	String cond;
	String count;
	ArrayList<Tag> children;

	public NoInput(String cond, String count) {
		this.cond = cond;
		this.count = count;
		children = new ArrayList<Tag>();
		identifier = 1;
	}

	public String GetCondition() {
		return cond;

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
		if (cond != null) {
			if (cond.contains("==")) {
				String firstOP = cond.split("==")[0].replace(" ", "").replace(
						"\'", "");
				String secondOP = cond.split("==")[1].replace(" ", "").replace(
						"\'", "");

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

					if (((StateVariables) o).VariableHashMap.get(firstOP)
							.equals(secondOP)) { // not
						// equal
						return null;
					}
				}
			}
		}
		for (Tag t : children) {

			t.eval(o);

		}

		return null;
	}

	public ArrayList<Tag> getChildren() {
		return children;
	}
}
