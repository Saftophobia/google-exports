package syntaxAnalyzer;

public class ClassMemberDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)FieldDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)MethodDeclaration.eval(o)) ){
		return true;
	}

	return false;
}
}
