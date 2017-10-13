package syntaxAnalyzer;

public class ClassOrInterfaceType{

public static Object eval(Object o ){

	if( 	((Boolean)ClassType.eval(o)) ){
		return true;
	}


	if( 	((Boolean)InterfaceType.eval(o)) ){
		return true;
	}

	return false;
}
}
