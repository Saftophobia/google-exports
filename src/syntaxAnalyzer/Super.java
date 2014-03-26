package syntaxAnalyzer;

public class Super{

public static Object eval(Object o ){

	if( 	"extends".equals((String)o) &&
 	((Boolean)ClassType.eval(o)) ){
		return true;
	}

	return false;

}
}
