package vxmlModel;

import util.StateVariables;

public abstract class Tag {
	
	protected int identifier = -1;
	Tag parent;
	public abstract Object eval(StateVariables o);
	
}
