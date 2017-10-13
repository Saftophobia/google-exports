package syntaxAnalyzer;

public class TypeDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)ClassDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)InterfaceDeclaration.eval(o)) ){
		return true;
	}


	if( 	";".equals((String)o) ){
		return true;
	}

	return false;
}
}
