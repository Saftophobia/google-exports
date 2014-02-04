package vxmlModel;
import java.util.ArrayList;

import util.StateVariables;


public class Grammar extends TagHolder{


	String fetchHint;	
	String fetchtimeout;
	String mode;	
	String root;	
	String scope;	
	String src;	 
	String srcexpr;	
	String tagFormat;	
	String type;	
	String version;
	String voxeo_useuri;
	String weight;
	String xml_base;
	String xml_lang;
	String xmlns;
	
	ArrayList<Tag> rules;
	
	public Grammar(String fetchHint, String fetchtimeout, String mode,
			String root, String scope, String src, String srcexpr,
			String tagFormat, String type, String version, String voxeo_useuri,
			String weight, String xml_base, String xml_lang, String xmlns) {
		super();
		this.fetchHint = fetchHint;
		this.fetchtimeout = fetchtimeout;
		this.mode = mode;
		this.root = root;
		this.scope = scope;
		this.src = src;
		this.srcexpr = srcexpr;
		this.tagFormat = tagFormat;
		this.type = type;
		this.version = version;
		this.voxeo_useuri = voxeo_useuri;
		this.weight = weight;
		this.xml_base = xml_base;
		this.xml_lang = xml_lang;
		this.xmlns = xmlns;
		this.rules = new ArrayList<Tag>();
		this.identifier = 10;
	}
	
	public void addChild(Rule child){
		child.parent = this;
		rules.add(child);
	}
	
	public Rule getChild(){
		return (Rule) rules.get(parsingIndex++);
	}
	
	public void updateParsingIndex(int i){
		parsingIndex = i;
	}

	@Override
	public Object eval(Object o) {
		for(Tag t:this.rules)
		{
			if((boolean) t.eval((String)o))
			{
				return true;
			}
		}
		return false;
	}

	public ArrayList<Tag> getChildren() {
		return rules;
	}	
	
}
