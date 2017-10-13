package syntaxAnalyzer;

public class SignedInteger{

public static Object eval(Object o ){

	if( 	((Boolean)Digits.eval(o)) ){
		return true;
	}


	if( 	((Boolean)Sign.eval(o)) &&
 	((Boolean)Digits.eval(o)) ){
		return true;
	}

	return false;

}
}
