package vxmlModel;
import java.util.ArrayList;

import util.StateVariables;


public class OneOf extends TagHolder{
	
	ArrayList<Tag> children;
	
	public OneOf(){
		this.children = new ArrayList<Tag>();
	}
	
	public void addChild(Tag child){
		child.parent = this;
		children.add(child);
	}
	
	public Tag getChild(){
		return children.get(parsingIndex++);
	}
	
	public void updateParsingIndex(int i){
		parsingIndex = i;
	}
	
	public ArrayList<Tag> getTagsByType(int identifier){
		ArrayList<Tag> output = new ArrayList<Tag>();
		for(Tag child:children){
			if(child.identifier == identifier){
				output.add(child);
			}
		}
		return output;
	}

	@Override
	public Object eval(Object o) {
		for(Tag t:this.children)
		{
			if((boolean)t.eval((String)o))
			{
				return true;
			}
		}
		return false;
	}
	
	
	public ArrayList<Tag> getChildren() {
		return children;
	}
}
