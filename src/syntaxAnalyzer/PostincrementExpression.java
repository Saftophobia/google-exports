package syntaxAnalyzer;

public class PostincrementExpression{

public static Object eval(Object o ){

	if( 	((Boolean)PostfixExpression.eval(o)) &&
 	"++".equals((String)o) ){
		return true;
	}

	return false;

}
}
