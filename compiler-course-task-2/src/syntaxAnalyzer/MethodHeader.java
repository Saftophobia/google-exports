package syntaxAnalyzer;

public class MethodHeader{

public static Object eval(Object o ){

	if( 	((Boolean)ResultType.eval(o)) &&
 	((Boolean)MethodDeclarator.eval(o)) ){
		return true;
	}


	if( 	((Boolean)MethodModifiers.eval(o)) &&
 	((Boolean)ResultType.eval(o)) &&
 	((Boolean)MethodDeclarator.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ResultType.eval(o)) &&
 	((Boolean)MethodDeclarator.eval(o)) &&
 	((Boolean)Throws.eval(o)) ){
		return true;
	}


	if( 	((Boolean)MethodModifiers.eval(o)) &&
 	((Boolean)ResultType.eval(o)) &&
 	((Boolean)MethodDeclarator.eval(o)) &&
 	((Boolean)Throws.eval(o)) ){
		return true;
	}

	return false;

}
}
