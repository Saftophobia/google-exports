package syntaxAnalyzer;

public class AbstractMethodDeclaration{

public static Object eval(Object o ){

	if( 	((Boolean)ResultType.eval(o)) &&
 	((Boolean)MethodDeclarator.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)AbstractMethodModifiers.eval(o)) &&
 	((Boolean)ResultType.eval(o)) &&
 	((Boolean)MethodDeclarator.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)ResultType.eval(o)) &&
 	((Boolean)MethodDeclarator.eval(o)) &&
 	((Boolean)Throws.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)AbstractMethodModifiers.eval(o)) &&
 	((Boolean)ResultType.eval(o)) &&
 	((Boolean)MethodDeclarator.eval(o)) &&
 	((Boolean)Throws.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
