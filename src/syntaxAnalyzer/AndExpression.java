package syntaxAnalyzer;

public class AndExpression{

public static Object eval(Object o ){

	if( 	((Boolean)EqualityExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)AndExpression.eval(o)) &&
 	"&".equals((String)o) &&
 	((Boolean)EqualityExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
