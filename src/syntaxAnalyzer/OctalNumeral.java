package syntaxAnalyzer;

public class OctalNumeral{

public static Object eval(Object o ){

	if( 	"0".equals((String)o) &&
 	((Boolean)OctalDigit.eval(o)) ){
		return true;
	}


	if( 	((Boolean)OctalNumeral.eval(o)) &&
 	((Boolean)OctalDigit.eval(o)) ){
		return true;
	}

	return false;
}
}
