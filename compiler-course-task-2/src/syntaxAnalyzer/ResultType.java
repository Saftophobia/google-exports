package syntaxAnalyzer;

public class ResultType{

public static Object eval(Object o ){

	if( 	((Boolean)Type.eval(o)) ){
		return true;
	}


	if( 	"void".equals((String)o) ){
		return true;
	}

	return false;
}
}
