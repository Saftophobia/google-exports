package syntaxAnalyzer;

public class ConditionalOrExpression{

public static Object eval(Object o ){

	if( 	((Boolean)ConditionalAndExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ConditionalOrExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ConditionalAndExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
