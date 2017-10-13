package syntaxAnalyzer;

public class MethodBody{

public static Object eval(Object o ){

	if( 	((Boolean)Block.eval(o)) ){
		return true;
	}


	if( 	";".equals((String)o) ){
		return true;
	}

	return false;
}
}
