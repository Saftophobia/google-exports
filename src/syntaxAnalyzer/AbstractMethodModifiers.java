package syntaxAnalyzer;

public class AbstractMethodModifiers{

public static Object eval(Object o ){

	if( 	((Boolean)AbstractMethodModifier.eval(o)) ){
		return true;
	}


	if( 	((Boolean)AbstractMethodModifiers.eval(o)) &&
 	((Boolean)AbstractMethodModifier.eval(o)) ){
		return true;
	}

	return false;
}
}
