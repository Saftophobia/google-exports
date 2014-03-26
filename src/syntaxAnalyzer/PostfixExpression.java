package syntaxAnalyzer;

public class PostfixExpression{

public static Object eval(Object o ){

	if( 	((Boolean)Primary.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ExpressionName.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PostincrementExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PostdecrementExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
