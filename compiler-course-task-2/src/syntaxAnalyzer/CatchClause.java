package syntaxAnalyzer;

public class CatchClause{

public static Object eval(Object o ){

	if( 	"catch".equals((String)o) &&
 	"(".equals((String)o) &&
 	((Boolean)FormalParameter.eval(o)) &&
 	")".equals((String)o) &&
 	((Boolean)Block.eval(o)) ){
		return true;
	}

	return false;

}
}
