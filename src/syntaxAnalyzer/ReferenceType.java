package syntaxAnalyzer;

public class ReferenceType{

public static Object eval(Object o ){

	if( 	((Boolean)ClassOrInterfaceType.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ArrayType.eval(o)) ){
		return true;
	}

	return false;
}
}
