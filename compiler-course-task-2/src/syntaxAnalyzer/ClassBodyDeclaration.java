package syntaxAnalyzer;

public class ClassBodyDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)ClassMemberDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)StaticInitializer.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ConstructorDeclaration.eval(o)) ){
		return true;
	}

	return false;
}
}
