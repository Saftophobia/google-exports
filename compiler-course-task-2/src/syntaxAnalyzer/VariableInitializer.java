package syntaxAnalyzer;

public class VariableInitializer{

public static Object eval(Object o ){

	if( 	((Boolean)Expression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ArrayInitializer.eval(o)) ){
		return true;
	}

	return false;
}
}
