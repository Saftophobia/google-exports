package syntaxAnalyzer;

public class CompilationUnit{

public static Object eval(Object o ){

	if( 	((Boolean)PackageDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ImportDeclarations.eval(o)) ){
		return true;
	}


	if( 	((Boolean)TypeDeclarations.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PackageDeclaration.eval(o)) &&
 	((Boolean)ImportDeclarations.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PackageDeclaration.eval(o)) &&
 	((Boolean)TypeDeclarations.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ImportDeclarations.eval(o)) &&
 	((Boolean)TypeDeclarations.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PackageDeclaration.eval(o)) &&
 	((Boolean)ImportDeclarations.eval(o)) &&
 	((Boolean)TypeDeclarations.eval(o)) ){
		return true;
	}

	return false;

}
}
