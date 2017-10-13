package syntaxAnalyzer;

public class StringLiteral{

public static Object eval(Object o ){

	if( 	"\"".equals((String)o) &&
 	"\"".equals((String)o) ){
		return true;
	}


	if( 	"\"".equals((String)o) &&
 	((Boolean)StringCharacters.eval(o)) &&
 	"\"".equals((String)o) ){
		return true;
	}

	return false;

}
}
