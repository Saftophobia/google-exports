package vxmlModel;

import util.StateVariables;

public class Reprompt extends DataHolder{

	
	public Reprompt(){
		
	}

	@Override
	public Object eval(Object o) {
		return ((StateVariables)o).LastPrompt.eval(o);
	}
	
}
