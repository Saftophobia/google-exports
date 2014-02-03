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
	
	ArrayList<Rule> rules;
	
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
		this.rules = new ArrayList<Rule>();
	}
	
	public void addChild(Rule child){
		child.parent = this;
		rules.add(child);
	}
	
	public Rule getChild(){
		return rules.get(parsingIndex++);
	}
	
	public void updateParsingIndex(int i){
		parsingIndex = i;
	}

	@Override
	public Object eval(StateVariables o) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean evalValue(String s) {
		for(Tag t:this.rules)
		{
			if(((Rule) t).evalValue(s))
			{
				return true;
			}
		}
		return false;
	}

	
	
}
