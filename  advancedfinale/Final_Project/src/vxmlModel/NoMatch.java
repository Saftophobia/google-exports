package vxmlModel;
import java.util.ArrayList;

import util.FreeTTSListener;
import util.StateVariables;

public class NoMatch extends TagHolder {

	String cond;
	String count;
	ArrayList<Tag> children;

	public NoMatch(String cond, String count) {
		this.cond =cond;
		this.count = count;
		children = new ArrayList<Tag>();
		identifier = 2;
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
	public Object eval(Object o) {

		Object[] input = (Object[]) o;
		StateVariables s = (StateVariables) input[0];
		int count = (Integer) input[1];
		
		if (cond != null) {
			if (cond.contains("==")) {
				String firstOP = cond.split("==")[0].replace(" ", "")
						.replace("\'", "");
				String secondOP = cond.split("==")[1].replace(" ", "")
						.replace("\'", "");

				if (!s.VariableHashMap.get(firstOP).equals(secondOP)) { // not
																			// equal
					return null;
				}
			} else {
				if (cond.contains("!=")) {
					String firstOP = cond.split("!=")[0].replace(" ", "")
							.replace("\'", "");
					String secondOP = cond.split("!=")[1].replace(" ", "")
							.replace("\'", "");

					if (s.VariableHashMap.get(firstOP).equals(secondOP)) { // not
																				// equal
						return null;
					}
				}
			}
		}
		
		if(Integer.parseInt(this.count) != count){
			return null;
		}
		
		for (Tag t : children) {
			if(t instanceof Value)
			{
				for (FreeTTSListener listener : ((StateVariables)o).Listerners) {
					listener.Say((String)t.eval(o));
				}
			}else{
			t.eval(s);
			}
		}
		
		return null;
	}

}
