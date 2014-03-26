package syntaxAnalyzer;

public class ForUpdate{

public static Object eval(Object o ){

	if( 	((Boolean)StatementExpressionList.eval(o)) ){
		return true;
	}

	return false;

}
}
