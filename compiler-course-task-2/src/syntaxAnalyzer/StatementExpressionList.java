package syntaxAnalyzer;

public class StatementExpressionList{

public static Object eval(Object o ){

	if( 	((Boolean)StatementExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)StatementExpressionList.eval(o)) &&
 	",".equals((String)o) &&
 	((Boolean)StatementExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
