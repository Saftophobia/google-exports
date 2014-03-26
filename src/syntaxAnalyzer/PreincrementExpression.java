package syntaxAnalyzer;

public class PreincrementExpression{

public static Object eval(Object o ){

	if( 	"++".equals((String)o) &&
 	((Boolean)UnaryExpression.eval(o)) ){
		return true;
	}

	return false;

}
}
