package vxmlModel;
import java.util.ArrayList;

import util.StateVariables;

public class Vxml extends TagHolder{
	
	String application;
	String version;
	String xml_base;
	String xml_lang;
	String xmlns;
	String xmlns_voxeo;
	ArrayList<Tag> children;
	
	public Vxml(String application, String version, String xml_base,
			String xml_lang, String xmlns, String xmlns_voxeo) {
		super();
		this.application = application;
		this.version = version;
		this.xml_base = xml_base;
		this.xml_lang = xml_lang;
		this.xmlns = xmlns;
		this.xmlns_voxeo = xmlns_voxeo;
		children =  new ArrayList<Tag>();	
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
	public Object eval(Object o){
		for(Tag t:this.children )
		{
			t.eval(o);
		}
		return children;
		
	}

	public ArrayList<Tag> getChildren() {
		return children;
	}
	

}