package syntaxAnalyzer;

public class ContinueStatement{

public static Object eval(Object o ){

	if( 	"continue".equals((String)o) &&
 	";".equals((String)o) ){
		return true;
	}


	if( 	"continue".equals((String)o) &&
 	((Boolean)Identifier.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
