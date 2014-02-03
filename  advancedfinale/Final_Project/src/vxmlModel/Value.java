package vxmlModel;

import javax.script.Invocable;
import javax.script.ScriptException;

import util.FreeTTSListener;
import util.StateVariables;

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
	public Object eval(StateVariables o) {
		if(expr != null)
		{
			if (expr.endsWith(")")) // a function
			{

				String FunctionName = expr.substring(0, expr.indexOf("("));
				String[] FunctionArgument = expr.substring(expr.indexOf("("),
						expr.indexOf(")")).split(",");
				for (int i = 0; i < FunctionArgument.length; i++) {
					FunctionArgument[i] = o.VariableHashMap.get(FunctionArgument[i]);
				}
				
				try {
					Invocable inv = (Invocable) o.engine;

					String Output = inv.invokeFunction(FunctionName,
							FunctionArgument) + "";
					
										
					return Output;
				} catch (Exception e1) {
					// can't find the method
				}
				// }
			} else { //not a function
				
				return o.VariableHashMap.get(expr);
			}
		}
		
		return null;
	}
	
	
}
