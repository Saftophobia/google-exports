package syntaxAnalyzer;

public class AdditiveExpression{

public static Object eval(Object o ){

	if( 	((Boolean)MultiplicativeExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)AdditiveExpression.eval(o)) &&
 	"+".equals((String)o) &&
 	((Boolean)MultiplicativeExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)AdditiveExpression.eval(o)) &&
 	"-".equals((String)o) &&
 	((Boolean)MultiplicativeExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
