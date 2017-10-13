package syntaxAnalyzer;

public class AssignmentExpression{

public static Object eval(Object o ){

	if( 	((Boolean)ConditionalExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)Assignment.eval(o)) ){
		return true;
	}

	return false;
}
}
