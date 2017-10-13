package syntaxAnalyzer;

public class MultiplicativeExpression{

public static Object eval(Object o ){

	if( 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)MultiplicativeExpression.eval(o)) &&
 	"*".equals((String)o) &&
 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)MultiplicativeExpression.eval(o)) &&
 	"/".equals((String)o) &&
 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)MultiplicativeExpression.eval(o)) &&
 	"%".equals((String)o) &&
 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
