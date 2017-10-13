package syntaxAnalyzer;

public class WhileStatement{

public static Object eval(Object o ){

	if( 	"while".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)Statement.eval(o)) ){
		return true;
	}

	return false;

}
}
