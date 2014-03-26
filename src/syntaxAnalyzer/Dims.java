package syntaxAnalyzer;

public class Dims{

public static Object eval(Object o ){

	if( 	"[".equals((String)o) &&
 	"]".equals((String)o) ){
		return true;
	}


	if( 	((Boolean)Dims.eval(o)) &&
 	"[".equals((String)o) &&
 	"]".equals((String)o) ){
		return true;
	}

	return false;
}
}
