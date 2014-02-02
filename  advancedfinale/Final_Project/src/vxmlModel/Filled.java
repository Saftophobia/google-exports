package vxmlModel;
import java.util.ArrayList;


public class Filled extends TagHolder{

	String mode;
	String nameList;
	ArrayList<Tag> children;
	
	public Filled(String mode, String nameList) {
		super();
		this.mode = mode;
		this.nameList = nameList;
		this.children = new ArrayList<Tag>();
	}
	public String getMode() {
		return mode;
	}
	public String getNameList() {
		return nameList;
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
