package syntaxAnalyzer;

public class Finally{

public static Object eval(Object o ){

	if( 	"finally".equals((String)o) &&
 	((Boolean)Block.eval(o)) ){
		return true;
	}

	return false;

}
}
