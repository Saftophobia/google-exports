package syntaxAnalyzer;

public class InterfaceMemberDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)ConstantDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)AbstractMethodDeclaration.eval(o)) ){
		return true;
	}

	return false;
}
}
