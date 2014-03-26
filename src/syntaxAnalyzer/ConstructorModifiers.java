package syntaxAnalyzer;

public class ConstructorModifiers{

public static Object eval(Object o ){

	if( 	((Boolean)ConstructorModifier.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ConstructorModifiers.eval(o)) &&
 	((Boolean)ConstructorModifier.eval(o)) ){
		return true;
	}

	return false;
}
}
