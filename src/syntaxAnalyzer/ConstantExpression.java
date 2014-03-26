package syntaxAnalyzer;

public class ConstantExpression{

public static Object eval(Object o ){

	if( 	((Boolean)Expression.eval(o)) ){
		return true;
	}

	return false;

}
}
