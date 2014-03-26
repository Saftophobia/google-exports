package syntaxAnalyzer;

public class TypeName{

public static Object eval(Object o ){

	if( 	((Boolean)Identifier.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PackageName.eval(o)) &&
 	".".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) ){
		return true;
	}

	return false;
}
}
