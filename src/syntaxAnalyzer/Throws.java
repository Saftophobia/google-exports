package syntaxAnalyzer;

public class Throws{

public static Object eval(Object o ){

	if( 	"throws".equals((String)o) &&
 	((Boolean)ClassTypeList.eval(o)) ){
		return true;
	}

	return false;

}
}
