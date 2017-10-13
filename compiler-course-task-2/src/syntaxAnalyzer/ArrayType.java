package syntaxAnalyzer;

public class ArrayType{

public static Object eval(Object o ){

	if( 	((Boolean)Type.eval(o)) &&
 	"[".equals((String)o) &&
 	"]".equals((String)o) ){
		return true;
	}

	return false;

}
}
