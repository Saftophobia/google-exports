package syntaxAnalyzer;

public class ImportDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)SingleTypeImportDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)TypeImportOnDemandDeclaration.eval(o)) ){
		return true;
	}

	return false;
}
}
