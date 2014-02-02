package vxmlModel;
import java.util.ArrayList;


public class Field extends TagHolder{
	
	String condition;
	String expr;
	String modal;
	String name;
	String type;
	String slot;
	ArrayList<Tag> children;
	
	public Field(String condition, String expr, String modal, String name,
			String type, String slot) {
		super();
		this.condition = condition;
		this.expr = expr;
		this.modal = modal;
		this.name = name;
		this.type = type;
		this.slot = slot;
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
	
	public String getCondition() {
		return condition;
	}

	public String getExpr() {
		return expr;
	}

	public String getModal() {
		return modal;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getSlot() {
		return slot;
	}

	public ArrayList<Tag> getChildren() {
		return children;
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
