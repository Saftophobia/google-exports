package syntaxAnalyzer;

public class ArgumentList{

public static Object eval(Object o ){

	if( 	((Boolean)Expression.eval(o)) ){
		return true;
	}


	if( 	((Boolean)ArgumentList.eval(o)) &&
 	",".equals((String)o) &&
 	((Boolean)Expression.eval(o)) ){
		return true;
	}

	return false;
}
}
