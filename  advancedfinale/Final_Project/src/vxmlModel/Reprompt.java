package vxmlModel;

import util.StateVariables;

public class Reprompt extends DataHolder{

	
	public Reprompt(){
		
	}

	@Override
	public Object eval(StateVariables o) {
		// TODO Auto-generated method stub
		return o.LastPrompt;
	}
	
}
