package vxmlModel;

import java.util.ArrayList;

import util.HelpListener;
import util.StateVariables;

public class Form extends TagHolder implements HelpListener{
	String id;
	String scope;
	ArrayList<Tag> children;
	boolean alreadyVisited = false;

	public Form(String id, String scope) {
		this.id = id;
		this.scope = scope;
		children = new ArrayList<Tag>();
		identifier = 1;
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
		alreadyVisited = true;
		((StateVariables) o).LastForm = this;
		for (Tag t : children) {
			if (t instanceof Block) {
				if (!((Block) t).alreadyVisited)
					t.eval(o);
			} else {
				if (t instanceof Field) {
					if (!((Field) t).alreadyVisited) {
						t.eval(o);
					}

				} else {
					t.eval(o);
				}
			}

		}
		return null;
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
