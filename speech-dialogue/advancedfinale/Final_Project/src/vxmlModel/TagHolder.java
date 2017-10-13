package vxmlModel;

import java.util.ArrayList;

public abstract class TagHolder extends Tag{

	int parsingIndex = 0;
	
	
	public abstract ArrayList<Tag> getChildren();

}
