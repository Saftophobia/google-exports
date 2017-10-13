package syntaxAnalyzer;

public class VariableDeclarators{

public static Object eval(Object o ){

	if( 	((Boolean)VariableDeclarator.eval(o)) ){
		return true;
	}


	if( 	((Boolean)VariableDeclarators.eval(o)) &&
 	",".equals((String)o) &&
 	((Boolean)VariableDeclarator.eval(o)) ){
		return true;
	}

	return false;
}
}
