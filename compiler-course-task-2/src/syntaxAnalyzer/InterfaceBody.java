package syntaxAnalyzer;

public class InterfaceBody{

public static Object eval(Object o ){

	if( 	"{".equals((String)o) &&
 	"}".equals((String)o) ){
		return true;
	}


	if( 	"{".equals((String)o) &&
 	((Boolean)InterfaceMemberDeclarations.eval(o)) &&
 	"}".equals((String)o) ){
		return true;
	}

	return false;

}
}
