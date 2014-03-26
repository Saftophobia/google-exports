package syntaxAnalyzer;

public class StatementWithoutTrailingSubstatement{

public static Object eval(Object o ){

	if( 	((Boolean)Block.eval(o)) ){
		return true;
	}


	if( 	((Boolean)EmptyStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ExpressionStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)SwitchStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)DoStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)BreakStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ContinueStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ReturnStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)SynchronizedStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ThrowsStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)TryStatement.eval(o)) ){
		return true;
	}

	return false;
}
}
