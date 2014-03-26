package syntaxAnalyzer;

public class CompilationUnit{

public Object eval(Object o ){

	if( 	PackageDeclaration.eval(o) ){
		return true;
	}


	if( 	ImportDeclarations.eval(o) ){
		return true;
	}


	if( 	TypeDeclarations.eval(o) ){
		return true;
	}


	if( 	PackageDeclaration.eval(o) &&
 	ImportDeclarations.eval(o) ){
		return true;
	}


	if( 	PackageDeclaration.eval(o) &&
 	TypeDeclarations.eval(o) ){
		return true;
	}


	if( 	ImportDeclarations.eval(o) &&
 	TypeDeclarations.eval(o) ){
		return true;
	}


	if( 	PackageDeclaration.eval(o) &&
 	ImportDeclarations.eval(o) &&
 	TypeDeclarations.eval(o) ){
		return true;
	}

	return false;

}
}
