package syntaxAnalyzer;

public class InterfaceModifiers{

public static Object eval(Object o ){

	if( 	((Boolean)InterfaceModifier.eval(o)) ){
		return true;
	}


	if( 	((Boolean)InterfaceModifiers.eval(o)) &&
 	((Boolean)InterfaceModifier.eval(o)) ){
		return true;
	}

	return false;
}
}
