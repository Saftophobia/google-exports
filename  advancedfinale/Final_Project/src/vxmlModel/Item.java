package vxmlModel;
import java.util.ArrayList;


public class Item extends TagHolder{


	String weight;
	String prob;
	String repeat;
	
	ArrayList<Tag> children; 
	
	public Item(String weight, String prob, String repeat) {
		super();
		this.weight = weight;
		this.prob = prob;
		this.repeat = repeat;
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
		// TODO Auto-generated method stub
		return null;
	}
	
}
