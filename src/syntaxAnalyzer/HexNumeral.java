package syntaxAnalyzer;

public class HexNumeral{

public static Object eval(Object o ){

	if( 	"0".equals((String)o) &&
 	"x".equals((String)o) &&
 	((Boolean)HexDigit.eval(o)) ){
		return true;
	}


	if( 	"0".equals((String)o) &&
 	"X".equals((String)o) &&
 	((Boolean)HexDigit.eval(o)) ){
		return true;
	}


	if( 	((Boolean)HexNumeral.eval(o)) &&
 	((Boolean)HexDigit.eval(o)) ){
		return true;
	}

	return false;
}
}
