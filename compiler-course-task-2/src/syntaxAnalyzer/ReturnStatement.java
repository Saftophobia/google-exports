package syntaxAnalyzer;

public class ReturnStatement{

public static Object eval(Object o ){

	if( 	"return".equals((String)o) &&
 	";".equals((String)o) ){
		return true;
	}


	if( 	"return".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
