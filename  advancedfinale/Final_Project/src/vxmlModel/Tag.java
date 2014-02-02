package vxmlModel;

public abstract class Tag {
	
	protected int identifier = -1;
	Tag parent;
	public abstract Object eval(Object o);
	
}
