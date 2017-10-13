package syntaxAnalyzer;

public class ExclusiveOrExpression{

public static Object eval(Object o ){

	if( 	((Boolean)AndExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ExclusiveOrExpression.eval(o)) &&
 	"^".equals((String)o) &&
 	((Boolean)AndExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
