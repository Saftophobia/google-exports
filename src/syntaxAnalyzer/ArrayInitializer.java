package syntaxAnalyzer;

public class ArrayInitializer{

public static Object eval(Object o ){

	if( 	"{".equals((String)o) &&
 	",".equals((String)o) &&
 	"?".equals((String)o) &&
 	"}".equals((String)o) ){
		return true;
	}


	if( 	"{".equals((String)o) &&
 	((Boolean)VariableInitializers.eval(o)) &&
 	",".equals((String)o) &&
 	"?".equals((String)o) &&
 	"}".equals((String)o) ){
		return true;
	}

	return false;

}
}
