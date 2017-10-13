package syntaxAnalyzer;

public class Interfaces{

public static Object eval(Object o ){

	if( 	"implements".equals((String)o) &&
 	((Boolean)InterfaceTypeList.eval(o)) ){
		return true;
	}

	return false;

}
}
