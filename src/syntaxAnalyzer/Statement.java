package syntaxAnalyzer;

public class Statement{

public static Object eval(Object o ){

	if( 	((Boolean)StatementWithoutTrailingSubstatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)LabeledStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)IfThenStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)IfThenElseStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)WhileStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ForStatement.eval(o)) ){
		return true;
	}

	return false;
}
}
