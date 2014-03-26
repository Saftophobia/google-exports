package syntaxAnalyzer;

public class PostdecrementExpression{

public static Object eval(Object o ){

	if( 	((Boolean)PostfixExpression.eval(o)) &&
 	"--".equals((String)o) ){
		return true;
	}

	return false;

}
}
