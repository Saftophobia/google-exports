package syntaxAnalyzer;

public class IfThenElseStatement{

public static Object eval(Object o ){

	if( 	"if".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)StatementNoShortIf.eval(o)) &&
 	"else".equals((String)o) &&
 	((Boolean)Statement.eval(o)) ){
		return true;
	}

	return false;

}
}
