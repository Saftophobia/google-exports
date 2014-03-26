package syntaxAnalyzer;

public class ForStatementNoShortIf{

public static Object eval(Object o ){

	if( 	"for".equals((String)o) &&
 	"(".equals((String)o) &&
 	";".equals((String)o) &&
 	";".equals((String)o) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	"for".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)ForInit.eval(o)) &&
 	";".equals((String)o) &&
 	";".equals((String)o) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	"for".equals((String)o) &&
 	"(".equals((String)o) &&
 	";".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	";".equals((String)o) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	"for".equals((String)o) &&
 	"(".equals((String)o) &&
 	";".equals((String)o) &&
 	";".equals((String)o) &&
 	((Boolean)ForUpdate.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	"for".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)ForInit.eval(o)) &&
 	";".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	";".equals((String)o) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	"for".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)ForInit.eval(o)) &&
 	";".equals((String)o) &&
 	";".equals((String)o) &&
 	((Boolean)ForUpdate.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	"for".equals((String)o) &&
 	"(".equals((String)o) &&
 	";".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	";".equals((String)o) &&
 	((Boolean)ForUpdate.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}


	if( 	"for".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)ForInit.eval(o)) &&
 	";".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	";".equals((String)o) &&
 	((Boolean)ForUpdate.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}

	return false;

}
}
