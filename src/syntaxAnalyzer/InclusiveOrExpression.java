package syntaxAnalyzer;

public class InclusiveOrExpression{

public static Object eval(Object o ){

	if( 	((Boolean)ExclusiveOrExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)InclusiveOrExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ExclusiveOrExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
