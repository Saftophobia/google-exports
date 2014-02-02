package vxmlModel;
import java.util.ArrayList;

public class NoInput extends TagHolder {

	String cond;
	String count;
	ArrayList<Tag> children;

	public NoInput(String cond, String count) {
		this.cond =cond;
		this.count = count;
		children = new ArrayList<Tag>();
		identifier = 1;
	}

	
	public String GetCondition(){
		return cond;
		
	}
	
	public String GetCount(){
		return count;
	}
	
	public void addChild(Tag child) {
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

}
