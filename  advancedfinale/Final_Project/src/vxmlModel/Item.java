package vxmlModel;
import java.util.ArrayList;

import util.StateVariables;
import util.StaticMethods;


public class Item extends TagHolder{


	String weight;
	String prob;
	String repeat;
	String data;
	ArrayList<Tag> children; 
	
	public Item(String weight, String prob, String repeat,String data) {
		super();
		this.weight = weight;
		this.prob = prob;
		this.repeat = repeat;
		this.data = data;
		this.children = new ArrayList<Tag>();
	}



	public String getWeight() {
		return weight;
	}



	public String getProb() {
		return prob;
	}



	public String getRepeat() {
		return repeat;
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
		if(this.children.size() == 0)
		{
			System.out.println(this.data.toString());
			System.out.println(StaticMethods.Normalize((String)o));
			
			return StaticMethods.Normalize(this.data).equals(StaticMethods.Normalize((String)o));
		}
		for(Tag t:this.children)
		{
			if(t instanceof Item)
			{
				if((boolean) t.eval((String)o))
				{
					return true;
				}
			}else{
				if(t instanceof OneOf)
				{
					if((boolean) t.eval((String)o))
					{
						return true;
					}
				}else{
					return StaticMethods.Normalize(this.data).equals(StaticMethods.Normalize((String)o));
				}
			}
		}
		return false;
	}

	public ArrayList<Tag> getChildren() {
		return children;
	}
}
