package vxmlModel;
import java.util.ArrayList;


public class Block extends TagHolder{
	String name;
	String expr;
	String cond;
	ArrayList<Tag> children;
	
	public Block(String name, String expr, String cond){
		this.name = name;
		this.expr = expr;
		this.cond = cond;
		children = new ArrayList<Tag>();
		
	}
	
	public String GetCondition(){
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
