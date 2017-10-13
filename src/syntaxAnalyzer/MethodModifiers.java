package syntaxAnalyzer;

public class MethodModifiers{

public static Object eval(Object o ){

	if( 	((Boolean)MethodModifier.eval(o)) ){
		return true;
	}


	if( 	((Boolean)MethodModifiers.eval(o)) &&
 	((Boolean)MethodModifier.eval(o)) ){
		return true;
	}

	return false;
}
}
