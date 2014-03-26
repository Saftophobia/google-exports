package syntaxAnalyzer;

public class CharacterLiteral{

public static Object eval(Object o ){

	if( 	"'".equals((String)o) &&
 	((Boolean)SingleCharacter.eval(o)) &&
 	"'".equals((String)o) ){
		return true;
	}


	if( 	"'".equals((String)o) &&
 	((Boolean)EscapeSequence.eval(o)) &&
 	"'".equals((String)o) ){
		return true;
	}

	return false;
}
}
