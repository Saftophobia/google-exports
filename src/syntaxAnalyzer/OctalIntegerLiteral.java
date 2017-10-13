package syntaxAnalyzer;

public class OctalIntegerLiteral{

public static Object eval(Object o ){

	if( 	((Boolean)OctalNumeral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)OctalNumeral.eval(o)) &&
 	((Boolean)IntegerTypeSuffix.eval(o)) ){
		return true;
	}

	return false;

}
}
