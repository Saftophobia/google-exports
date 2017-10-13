package syntaxAnalyzer;

public class ExpressionStatement{

public static Object eval(Object o ){

	if( 	((Boolean)StatementExpression.eval(o)) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
