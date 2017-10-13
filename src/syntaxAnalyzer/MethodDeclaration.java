package syntaxAnalyzer;

public class MethodDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)MethodHeader.eval(o)) &&
 	((Boolean)MethodBody.eval(o)) ){
		return true;
	}

	return false;

}
}
