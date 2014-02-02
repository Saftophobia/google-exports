package vxmlModel;
import java.util.ArrayList;


public class Form extends TagHolder{
	String id;
	String scope;
	ArrayList<Tag> children;
	
	public Form(String id, String scope){
		this.id = id;
		this.scope = scope;
		children = new ArrayList<Tag>();
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
		// TODO Auto-generated method stub
		return null;
	}
}
