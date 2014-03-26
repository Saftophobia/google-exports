package syntaxAnalyzer;

public class FieldModifiers{

public static Object eval(Object o ){

	if( 	((Boolean)FieldModifier.eval(o)) ){
		return true;
	}


	if( 	((Boolean)FieldModifiers.eval(o)) &&
 	((Boolean)FieldModifier.eval(o)) ){
		return true;
	}

	return false;
}
}
