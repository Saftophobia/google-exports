package syntaxAnalyzer;

public class UnaryExpression{

public static Object eval(Object o ){

	if( 	((Boolean)PreincrementExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PredecrementExpression.eval(o)) ){
		return true;
	}


	if( 	"+".equals((String)o) &&
 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}


	if( 	"-".equals((String)o) &&
 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)UnaryExpressionNotPlusMinus.eval(o)) ){
		return true;
	}

	return false;
}
}
