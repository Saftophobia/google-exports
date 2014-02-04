package vxmlModel;

import util.StateVariables;

public class Var extends DataHolder{
	
	String name;
	String expr;
	
	public Var(String name, String expr) {
		super();
		this.name = name;
		this.expr = expr;
	}
	
	
	public String getName(){
		return name;
	}
	
	public String getExpr(){
		return expr;
	}


	@Override
	public Object eval(Object o) {
		
		if(name != null && expr != null){
			((StateVariables)o).VariableHashMap.put(name, expr);
		}
		return null;
	}
	

}
