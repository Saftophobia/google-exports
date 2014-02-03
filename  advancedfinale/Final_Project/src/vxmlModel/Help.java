package vxmlModel;
import java.util.ArrayList;

import util.StateVariables;

public class Help extends TagHolder {

	String cond;
	String count;
	ArrayList<Tag> children;
	int parsingIndex = 0;

	public Help(String cond, String count) {
		this.cond =cond;
		this.count = count;
		children = new ArrayList<Tag>();
	}

	
	public String GetCondition(){
		return cond;
		
	}
	
	public String GetCount(){
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
	public Object eval(StateVariables o) {
		// TODO Auto-generated method stub
		
		if (cond != null) {
			if (cond.contains("==")) {
				String firstOP = cond.split("==")[0].replace(" ", "")
						.replace("\'", "");
				String secondOP = cond.split("==")[1].replace(" ", "")
						.replace("\'", "");

				if (o.VariableHashMap.get(firstOP) != secondOP) { // not
																			// equal
					return null;
				}
			} else {
				if (cond.contains("!=")) {
					String firstOP = cond.split("!=")[0].replace(" ", "")
							.replace("\'", "");
					String secondOP = cond.split("!=")[1].replace(" ", "")
							.replace("\'", "");

					if (o.VariableHashMap.get(firstOP) == secondOP) { // not
																				// equal
						return null;
					}
				}
			}
		}
		
		for(Tag t: children)
		{
			t.eval(o);
		}
		
		return null;
	}

}
