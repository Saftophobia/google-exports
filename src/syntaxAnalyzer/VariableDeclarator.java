package syntaxAnalyzer;

public class VariableDeclarator{

public static Object eval(Object o ){

	if( 	((Boolean)VariableDeclaratorId.eval(o)) ){
		return true;
	}


	if( 	((Boolean)VariableDeclaratorId.eval(o)) &&
 	"=".equals((String)o) &&
 	((Boolean)VariableInitializer.eval(o)) ){
		return true;
	}

	return false;
}
}
