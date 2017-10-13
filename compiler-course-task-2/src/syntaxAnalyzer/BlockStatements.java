package syntaxAnalyzer;

public class BlockStatements{

public static Object eval(Object o ){

	if( 	((Boolean)BlockStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)BlockStatements.eval(o)) &&
 	((Boolean)BlockStatement.eval(o)) ){
		return true;
	}

	return false;
}
}
