package vxmlModel;

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
	

}
