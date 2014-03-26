package syntaxAnalyzer;

public class AbstractMethodModifier{

public static Object eval(Object o ){

	if( 	"public".equals((String)o) ){
		return true;
	}


	if( 	"abstract".equals((String)o) ){
		return true;
	}

	return false;
}
}
