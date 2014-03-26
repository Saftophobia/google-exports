package syntaxAnalyzer;

public class DecimalNumeral{

public static Object eval(Object o ){

	if( 	"0".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)NonZeroDigit.eval(o)) ){
		return true;
	}


	if( 	((Boolean)NonZeroDigit.eval(o)) &&
 	((Boolean)Digits.eval(o)) ){
		return true;
	}

	return false;
}
}
