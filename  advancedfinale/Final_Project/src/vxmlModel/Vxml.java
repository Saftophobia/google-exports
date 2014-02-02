package vxmlModel;
import java.util.ArrayList;

public class Vxml extends TagHolder{
	
	String application;
	String version;
	String xml_base;
	String xml_land;
	String xmlns;
	String xmlns_voxeo;
	ArrayList<Tag> children;
	
	public Vxml(String application, String version, String xml_base,
			String xml_land, String xmlns, String xmlns_voxeo) {
		super();
		this.application = application;
		this.version = version;
		this.xml_base = xml_base;
		this.xml_land = xml_land;
		this.xmlns = xmlns;
		this.xmlns_voxeo = xmlns_voxeo;
		children =  new ArrayList<Tag>();	
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
