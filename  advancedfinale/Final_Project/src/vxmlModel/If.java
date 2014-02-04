package vxmlModel;

import java.util.ArrayList;

import util.FreeTTSListener;
import util.StateVariables;

public class If extends TagHolder {

	String cond;
	ArrayList<Tag> children;

	public If(String cond) {
		this.cond = cond;
		this.children = new ArrayList<Tag>();
	}

	public String getCond() {
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
		// TODO Auto-generated method stub
		boolean IfConditionisTrue = true;
		boolean elseIfvisited = false;

		if (cond.contains("==")) {
			String firstOP = cond.split("==")[0].replace(" ", "").replace("\'",
					"");
			String secondOP = cond.split("==")[1].replace(" ", "").replace(
					"\'", "");

			if (!((StateVariables) o).VariableHashMap.get(firstOP).equals(
					secondOP)) { // not
				// equal
				IfConditionisTrue = false;
			}
		} else {
			if (cond.contains("!=")) {
				String firstOP = cond.split("!=")[0].replace(" ", "").replace(
						"\'", "");
				String secondOP = cond.split("!=")[1].replace(" ", "").replace(
						"\'", "");

				if (((StateVariables) o).VariableHashMap.get(firstOP).equals(
						secondOP)) { // not
					// equal
					IfConditionisTrue = false;
				}
			}
		}

		for (Tag t : children) {
			if (IfConditionisTrue && !(t instanceof Elseif)
					&& !(t instanceof Else)) {
				if (t instanceof Value) {
					for (FreeTTSListener listener : ((StateVariables) o).Listerners) {
						listener.Say((String) t.eval(o));
					}
				} else {
					t.eval(o);
				}

			}
			if (!IfConditionisTrue && !elseIfvisited) {
				if (t instanceof Elseif) {
					if (cond.contains("==")) {
						String elseiffirstOP = ((Elseif) t).cond.split("==")[0]
								.replace(" ", "").replace("\'", "");
						String elseifsecondOP = ((Elseif) t).cond.split("==")[1]
								.replace(" ", "").replace("\'", "");

						if (((StateVariables) o).VariableHashMap.get(
								elseiffirstOP).equals(elseifsecondOP)) { // condition
							// satisfied
							IfConditionisTrue = true;
							elseIfvisited = true;

						}
					} else {
						if (cond.contains("!=")) {
							String elseiffirstOP = ((Elseif) t).cond
									.split("!=")[0].replace(" ", "").replace(
									"\'", "");
							String elseifsecondOP = ((Elseif) t).cond
									.split("!=")[1].replace(" ", "").replace(
									"\'", "");

							if (!((StateVariables) o).VariableHashMap.get(
									elseiffirstOP).equals(elseifsecondOP)) { // condition
								// satisfied
								IfConditionisTrue = true;
								elseIfvisited = true;

							}
						}
					}
				}
				if (t instanceof Else) {
					IfConditionisTrue = true;
				}
			}
		}
		return null;
	}

	public ArrayList<Tag> getChildren() {
		return children;
	}
}
