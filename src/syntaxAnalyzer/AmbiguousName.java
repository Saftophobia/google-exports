package syntaxAnalyzer;

public class AmbiguousName{

public static Object eval(Object o ){

	if( 	((Boolean)Identifier.eval(o)) ){
		return true;
	}


	if( 	((Boolean)AmbiguousName.eval(o)) &&
 	".".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) ){
		return true;
	}

	return false;
}
}
