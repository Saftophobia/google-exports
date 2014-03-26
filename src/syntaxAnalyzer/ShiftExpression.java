package syntaxAnalyzer;

public class ShiftExpression{

public static Object eval(Object o ){

	if( 	((Boolean)AdditiveExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ShiftExpression.eval(o)) &&
 	"<<".equals((String)o) &&
 	((Boolean)AdditiveExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ShiftExpression.eval(o)) &&
 	">>".equals((String)o) &&
 	((Boolean)AdditiveExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ShiftExpression.eval(o)) &&
 	">>>".equals((String)o) &&
 	((Boolean)AdditiveExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
