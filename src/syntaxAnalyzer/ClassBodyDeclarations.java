package syntaxAnalyzer;

public class ClassBodyDeclarations{

public static Object eval(Object o ){

	if( 	((Boolean)ClassBodyDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ClassBodyDeclarations.eval(o)) &&
 	((Boolean)ClassBodyDeclaration.eval(o)) ){
		return true;
	}

	return false;
}
}
