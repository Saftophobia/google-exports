package vxmlModel;
import java.util.ArrayList;


public class Prompt extends TagHolder{

	
	String bargein;
	String bargeinType;
	String condition;
	String count;
	String timeout;
	String xml_lang;
	String data;
	ArrayList<Tag> children; 
	
	public Prompt(String bargein, String bargeinType, String condition,
			String count, String timeout, String xml_lang, String data) {
		super();
		this.bargein = bargein;
		this.bargeinType = bargeinType;
		this.condition = condition;
		this.count = count;
		this.timeout = timeout;
		this.xml_lang = xml_lang;
		this.data = data;
		children =  new ArrayList<Tag>();
	}
	
	public String getData(){
		return data;
	}
	
	public String getTimeOut(){
		return timeout;
	}
	
	public String getBargein(){
		return bargein;
	}
	
	public String GetBargeinTsype(){
		return bargeinType;
	}
	
	public String GetCondition(){
		return condition;
		
	}
	
	public String GetCount(){
		return count;
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
