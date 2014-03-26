package syntaxAnalyzer;

public class NumericType{

public static Object eval(Object o ){

	if( 	((Boolean)IntegralType.eval(o)) ){
		return true;
	}


	if( 	((Boolean)FloatingPointType.eval(o)) ){
		return true;
	}

	return false;
}
}
