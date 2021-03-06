package vxmlModel;

import java.util.ArrayList;

import util.HelpListener;
import util.StateVariables;

public class Vxml extends TagHolder implements HelpListener{

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
		children = new ArrayList<Tag>();
	}

	public void addChild(Tag child) {
		child.parent = this;
		children.add(child);
	}

	public Tag getChild() {
		return children.get(parsingIndex++);
	}

	public void updateParsingIndex(int i) {
		parsingIndex = i;
	}

	public ArrayList<Tag> getTagsByType(int identifier) {
		ArrayList<Tag> output = new ArrayList<Tag>();
		for (Tag child : children) {
			if (child.identifier == identifier) {
				output.add(child);
			}
		}
		return output;
	}

	@Override
	public Object eval(Object o) {
		((StateVariables)o).currentHelpTag= this;
		for (Tag t : this.children) {
			if (t instanceof Form) {
				if (!((Form) t).alreadyVisited)
					t.eval(o);

			} else {
				t.eval(o);
			}
		}
		return children;

	}

	public ArrayList<Tag> getChildren() {
		return children;
	}

	@Override
	public void HelpMe(Object o) {
		// TODO Auto-generated method stub
		for(Tag t:this.children)
		{
			if(t instanceof Help)
			{
				t.eval(o);
			}
		}
	}

}