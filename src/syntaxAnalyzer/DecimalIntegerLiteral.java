package syntaxAnalyzer;

public class DecimalIntegerLiteral{

public static Object eval(Object o ){

	if( 	((Boolean)DecimalNumeral.eval(o)) ){
		return true;
	}


	if( 	((Boolean)DecimalNumeral.eval(o)) &&
 	((Boolean)IntegerTypeSuffix.eval(o)) ){
		return true;
	}

	return false;

}
}
