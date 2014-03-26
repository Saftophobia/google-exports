package syntaxAnalyzer;

public class ClassModifiers{

public static Object eval(Object o ){

	if( 	((Boolean)ClassModifier.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ClassModifiers.eval(o)) &&
 	((Boolean)ClassModifier.eval(o)) ){
		return true;
	}

	return false;
}
}
