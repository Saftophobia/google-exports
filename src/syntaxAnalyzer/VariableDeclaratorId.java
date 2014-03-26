package syntaxAnalyzer;

public class VariableDeclaratorId{

public static Object eval(Object o ){

	if( 	((Boolean)Identifier.eval(o)) ){
		return true;
	}


	if( 	((Boolean)VariableDeclaratorId.eval(o)) &&
 	"[".equals((String)o) &&
 	"]".equals((String)o) ){
		return true;
	}

	return false;
}
}
