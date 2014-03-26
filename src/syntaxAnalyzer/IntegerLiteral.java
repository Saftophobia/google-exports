package syntaxAnalyzer;

public class IntegerLiteral{

public static Object eval(Object o ){

	if( 	((Boolean)DecimalIntegerLiteral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)HexIntegerLiteral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)OctalIntegerLiteral.eval(o)) ){
		return true;
	}

	return false;
}
}
