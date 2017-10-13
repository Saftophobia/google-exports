package syntaxAnalyzer;

public class InterfaceMemberDeclarations{

public static Object eval(Object o ){

	if( 	((Boolean)InterfaceMemberDeclaration.eval(o)) ){
		return true;
	}


	if( 	((Boolean)InterfaceMemberDeclarations.eval(o)) &&
 	((Boolean)InterfaceMemberDeclaration.eval(o)) ){
		return true;
	}

	return false;
}
}
