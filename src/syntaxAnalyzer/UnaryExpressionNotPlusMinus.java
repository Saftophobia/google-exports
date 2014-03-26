package syntaxAnalyzer;

public class UnaryExpressionNotPlusMinus{

public static Object eval(Object o ){

	if( 	((Boolean)PostfixExpression.eval(o)) ){
		return true;
	}


	if( 	"~".equals((String)o) &&
 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}


	if( 	"!".equals((String)o) &&
 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)CastExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
