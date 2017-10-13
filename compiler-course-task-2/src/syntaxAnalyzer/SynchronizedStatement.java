package syntaxAnalyzer;

public class SynchronizedStatement{

public static Object eval(Object o ){

	if( 	"synchronized".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)Expression.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)Block.eval(o)) ){
		return true;
	}

	return false;

}
}
