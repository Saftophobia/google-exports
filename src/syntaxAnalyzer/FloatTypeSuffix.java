package syntaxAnalyzer;

public class FloatTypeSuffix{

public static Object eval(Object o ){

	if( 	"f".equals((String)o) ){
		return true;
	}


	if( 	"F".equals((String)o) ){
		return true;
	}


	if( 	"d".equals((String)o) ){
		return true;
	}


	if( 	"D".equals((String)o) ){
		return true;
	}

	return false;
}
}
