package syntaxAnalyzer;

public class HexIntegerLiteral{

public static Object eval(Object o ){

	if( 	((Boolean)HexNumeral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)HexNumeral.eval(o)) &&
 	((Boolean)IntegerTypeSuffix.eval(o)) ){
		return true;
	}

	return false;

}
}
