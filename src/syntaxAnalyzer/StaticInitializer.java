package syntaxAnalyzer;

public class StaticInitializer{

public static Object eval(Object o ){

	if( 	"static".equals((String)o) &&
 	((Boolean)Block.eval(o)) ){
		return true;
	}

	return false;

}
}
