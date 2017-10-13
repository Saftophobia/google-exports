package syntaxAnalyzer;

public class FieldAccess{

public static Object eval(Object o ){

	if( 	((Boolean)Primary.eval(o)) &&
 	".".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) ){
		return true;
	}


	if( 	"super".equals((String)o) &&
 	".".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) ){
		return true;
	}

	return false;
}
}
