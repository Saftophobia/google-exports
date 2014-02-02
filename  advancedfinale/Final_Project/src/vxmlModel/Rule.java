package vxmlModel;
import java.util.ArrayList;


public class Rule extends TagHolder{
	
	String  id ;
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

	public String getScope(){
		return scope;
	}
	
	public void addChild(Tag child){
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
	
	
}
