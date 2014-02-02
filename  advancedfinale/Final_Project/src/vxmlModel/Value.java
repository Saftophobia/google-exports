package vxmlModel;

public class Value extends DataHolder{
	
	String expr;

	public Value(String expr) {
		super();
		this.expr = expr;
	}

	public String getExpr() {
		return expr;
	}

	@Override
	public Object eval(Object o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
