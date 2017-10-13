package vxmlModel;

import util.StateVariables;

public class Elseif extends DataHolder{
	
	String cond;
	
	public Elseif(String cond){
		this.cond  = cond;
	}

	public String getCond(){
		return cond;
	}

	@Override
	public Object eval(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
