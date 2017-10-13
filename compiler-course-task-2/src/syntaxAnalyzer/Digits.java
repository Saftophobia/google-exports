package syntaxAnalyzer;

public class Digits{

public static Object eval(Object o ){

	if( 	((Boolean)Digit.eval(o)) ){
		return true;
	}


	if( 	((Boolean)Digits.eval(o)) &&
 	((Boolean)Digit.eval(o)) ){
		return true;
	}

	return false;
}
}
