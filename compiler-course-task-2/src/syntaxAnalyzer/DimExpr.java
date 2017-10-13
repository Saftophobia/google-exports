package syntaxAnalyzer;

public class DimExpr{

public static Object eval(Object o ){

	if( 	"[".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	"]".equals((String)o) ){
		return true;
	}

	return false;

}
}
