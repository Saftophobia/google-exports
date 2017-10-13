package syntaxAnalyzer;

public class ArrayAccess{

public static Object eval(Object o ){

	if( 	((Boolean)ExpressionName.eval(o)) &&
 	"[".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	"]".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)PrimaryNoNewArray.eval(o)) &&
 	"[".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	"]".equals((String)o) ){
		return true;
	}

	return false;
}
}
