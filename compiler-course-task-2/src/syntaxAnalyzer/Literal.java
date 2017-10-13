package syntaxAnalyzer;

public class Literal{

public static Object eval(Object o ){

	if( 	((Boolean)IntegerLiteral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)FloatingPointLiteral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)BooleanLiteral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)CharacterLiteral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)StringLiteral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)NullLiteral.eval(o)) ){
		return true;
	}

	return false;
}
}
