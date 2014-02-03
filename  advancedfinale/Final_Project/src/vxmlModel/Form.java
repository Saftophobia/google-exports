package vxmlModel;
import java.util.ArrayList;

import util.StateVariables;


public class Form extends TagHolder{
	String id;
	String scope;
	ArrayList<Tag> children;
	
	public Form(String id, String scope){
		this.id = id;
		this.scope = scope;
		children = new ArrayList<Tag>();
		identifier = 1;
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
	public Object eval(StateVariables o) {
		// TODO Auto-generated method stub
		for(Tag t:children)
		{
			t.eval(o);
		}
		return null;
	}
}
