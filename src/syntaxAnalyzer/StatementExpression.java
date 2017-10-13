package syntaxAnalyzer;

public class StatementExpression{

public static Object eval(Object o ){

	if( 	((Boolean)Assignment.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PreincrementExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PostincrementExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PredecrementExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)PostdecrementExpression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)MethodInvocation.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ClassInstanceCreationExpression.eval(o)) ){
		return true;
	}

	return false;
}
}
