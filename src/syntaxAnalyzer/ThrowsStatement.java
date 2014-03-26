package syntaxAnalyzer;

public class ThrowsStatement{

public static Object eval(Object o ){

	if( 	"throw".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
