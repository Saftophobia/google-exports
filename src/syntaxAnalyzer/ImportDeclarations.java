package syntaxAnalyzer;

public class ImportDeclarations{

public static Object eval(Object o ){

	if( 	((Boolean)ImportDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ImportDeclarations.eval(o)) &&
 	((Boolean)ImportDeclaration.eval(o)) ){
		return true;
	}

	return false;
}
}
