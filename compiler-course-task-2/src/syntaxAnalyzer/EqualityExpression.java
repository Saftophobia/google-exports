package syntaxAnalyzer;

public class EqualityExpression{

public static Object eval(Object o ){

	if( 	((Boolean)RelationalExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)EqualityExpression.eval(o)) &&
 	"==".equals((String)o) &&
 	((Boolean)RelationalExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)EqualityExpression.eval(o)) &&
 	"!=".equals((String)o) &&
 	((Boolean)RelationalExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
