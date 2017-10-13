package syntaxAnalyzer;

public class Catches{

public static Object eval(Object o ){

	if( 	((Boolean)CatchClause.eval(o)) ){
		return true;
	}


	if( 	((Boolean)Catches.eval(o)) &&
 	((Boolean)CatchClause.eval(o)) ){
		return true;
	}

	return false;
}
}
