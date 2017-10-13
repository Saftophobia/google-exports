package syntaxAnalyzer;

public class DoStatement{

public static Object eval(Object o ){

	if( 	"do".equals((String)o) &&
 	((Boolean)Statement.eval(o)) &&
 	"while".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	")".equals((String)o) &&
 	";".equals((String)o) ){
		return true;
	}

	return false;

}
}
