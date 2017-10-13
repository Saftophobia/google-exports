package syntaxAnalyzer;

public class ConditionalExpression{

public static Object eval(Object o ){

	if( 	((Boolean)ConditionalOrExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ConditionalOrExpression.eval(o)) &&
 	"?".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	":".equals((String)o) &&
 	((Boolean)ConditionalExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
