package syntaxAnalyzer;

public class TypeDeclarations{

public static Object eval(Object o ){

	if( 	((Boolean)TypeDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)TypeDeclarations.eval(o)) &&
 	((Boolean)TypeDeclaration.eval(o)) ){
		return true;
	}

	return false;
}
}
