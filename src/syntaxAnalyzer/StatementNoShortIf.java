package syntaxAnalyzer;

public class StatementNoShortIf{

public static Object eval(Object o ){

	if( 	((Boolean)StatementWithoutTrailingSubstatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)LabeledStatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	((Boolean)IfThenElseStatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	((Boolean)WhileStatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ForStatementNoShortIf.eval(o)) ){
		return true;
	}

	return false;
}
}
