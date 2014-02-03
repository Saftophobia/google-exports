package vxmlModel;
import java.util.ArrayList;

import util.FreeTTSListener;
import util.StateVariables;


public class Filled extends TagHolder{

	String mode;
	String nameList;
	ArrayList<Tag> children;
	
	public Filled(String mode, String nameList) {
		super();
		this.mode = "any";
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
	public Object eval(StateVariables o) {
		if(nameList != null)
		{
			String[] fieldvars = nameList.split(" ");
			if(mode.equalsIgnoreCase("all"))
			{
				for (int i = 0; i < fieldvars.length; i++) {
					if (o.VariableHashMap.get(fieldvars[i]) == null)
						return null;
				}
			}else{
				if(mode.equalsIgnoreCase("any"))
				{
					boolean found = false;
					for (int i = 0; i < fieldvars.length; i++) {
						if (o.VariableHashMap.get(fieldvars[i]) != null)
							found = true;
					}
					if (!found) {
						return null;
					}
				}
			}
			for (Tag t : children) {
				if(t instanceof Value)
				{
					for (FreeTTSListener listener : o.Listerners) {
						listener.Say((String)t.eval(o));
					}
				}else{
				t.eval(o);
				}
			}
		}
		return null;
	}
	
	
	
	
}
