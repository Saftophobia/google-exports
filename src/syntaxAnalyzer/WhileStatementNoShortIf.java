package syntaxAnalyzer;

public class WhileStatementNoShortIf{

public static Object eval(Object o ){

	if( 	"while".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) ){
		return true;
	}

	return false;

}
}
