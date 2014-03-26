package syntaxAnalyzer;

public class LeftHandSide{

public static Object eval(Object o ){

	if( 	((Boolean)ExpressionName.eval(o)) ){
		return true;
	}


	if( 	((Boolean)FieldAccess.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ArrayAccess.eval(o)) ){
		return true;
	}

	return false;
}
}
