package syntaxAnalyzer;

public class ConditionalAndExpression{

public static Object eval(Object o ){

	if( 	((Boolean)InclusiveOrExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ConditionalAndExpression.eval(o)) &&
 	"&&".equals((String)o) &&
 	((Boolean)InclusiveOrExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
