package syntaxAnalyzer;

public class RelationalExpression{

public static Object eval(Object o ){

	if( 	((Boolean)ShiftExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)RelationalExpression.eval(o)) &&
 	"<".equals((String)o) &&
 	((Boolean)ShiftExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)RelationalExpression.eval(o)) &&
 	">".equals((String)o) &&
 	((Boolean)ShiftExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)RelationalExpression.eval(o)) &&
 	"<=".equals((String)o) &&
 	((Boolean)ShiftExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)RelationalExpression.eval(o)) &&
 	">=".equals((String)o) &&
 	((Boolean)ShiftExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)RelationalExpression.eval(o)) &&
 	"instanceof".equals((String)o) &&
 	((Boolean)ReferenceType.eval(o)) ){
		return true;
	}

	return false;
}
}
