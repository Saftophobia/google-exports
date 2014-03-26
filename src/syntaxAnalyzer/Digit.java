package syntaxAnalyzer;

public class Digit{

public static Object eval(Object o ){

	if( 	"0".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)NonZeroDigit.eval(o)) ){
		return true;
	}

	return false;
}
}
