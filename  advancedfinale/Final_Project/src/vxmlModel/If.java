package vxmlModel;
import java.util.ArrayList;


public class If extends TagHolder{
	
	String cond;
	ArrayList<Tag> children;
	
	public If(String cond){
		this.cond  = cond;
		this.children = new ArrayList<Tag>();
	}

	public String getCond(){
		return cond;
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
