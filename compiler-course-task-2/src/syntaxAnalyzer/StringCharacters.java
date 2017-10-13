package syntaxAnalyzer;

public class StringCharacters{

public static Object eval(Object o ){

	if( 	((Boolean)StringCharacter.eval(o)) ){
		return true;
	}


	if( 	((Boolean)StringCharacters.eval(o)) &&
 	((Boolean)StringCharacter.eval(o)) ){
		return true;
	}

	return false;
}
}
