package vxmlModel;

import java.util.ArrayList;

import util.FreeTTSListener;
import util.StateVariables;
import util.StaticMethods;

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
			String firstOP = StaticMethods.Normalize(cond.split("==")[0]);
			String secondOP = StaticMethods.Normalize(cond.split("==")[1]);
			if (((StateVariables) o).VariableHashMap.get(firstOP) == null) {
				return null;
			}
			if (!((StateVariables) o).VariableHashMap.get(firstOP).equals(
					secondOP)) { // not
				// equal
				IfConditionisTrue = false;
			}
		} else {
			if (cond.contains("!=")) {
				String firstOP = StaticMethods.Normalize(cond.split("!=")[0]);
				String secondOP = StaticMethods.Normalize(cond.split("!=")[1]);

				if (((StateVariables) o).VariableHashMap.get(firstOP) == null) {
					return null;
				}
				if (((StateVariables) o).VariableHashMap.get(firstOP).equals(
						secondOP)) {
					IfConditionisTrue = false;
				}
			}
		}

		for (Tag t : children) {
			// true condition, execute
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
			// if If condition is false;
			if (t instanceof Elseif) {
				IfConditionisTrue = false;
				if (elseIfvisited == false) {
					if (((Elseif) t).cond.contains("==")) {
						String elseiffirstOP = StaticMethods
								.Normalize(((Elseif) t).cond.split("==")[0]);
						String elseifsecondOP = StaticMethods
								.Normalize(((Elseif) t).cond.split("==")[1]);
						if (((StateVariables) o).VariableHashMap
								.get(elseiffirstOP) == null) {
							return null;
						}
						if (((StateVariables) o).VariableHashMap.get(
								elseiffirstOP).equals(elseifsecondOP)) {
							IfConditionisTrue = true;
							elseIfvisited = true;
						}
					} else {
						if (((Elseif) t).cond.contains("!=")) {
							String elseiffirstOP = StaticMethods
									.Normalize(((Elseif) t).cond.split("!=")[0]);
							String elseifsecondOP = StaticMethods
									.Normalize(((Elseif) t).cond.split("==")[1]);

							if (((StateVariables) o).VariableHashMap
									.get(elseiffirstOP) == null) {
								return null;
							}
							if (!((StateVariables) o).VariableHashMap.get(
									elseiffirstOP).equals(elseifsecondOP)) { // condition
								// satisfied

								IfConditionisTrue = true;
								elseIfvisited = true;

							}
						}
					}
				}
			}
			if (t instanceof Else && elseIfvisited == false) {
				IfConditionisTrue = true;
			}
		}

		return null;
	}

	public ArrayList<Tag> getChildren() {
		return children;
	}
}
