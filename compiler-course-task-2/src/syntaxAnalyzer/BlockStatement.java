package syntaxAnalyzer;

public class BlockStatement{

public static Object eval(Object o ){

	if( 	((Boolean)LocalVariableDeclarationStatement.eval(o)) ){
		return true;
	}


	if( 	((Boolean)Statement.eval(o)) ){
		return true;
	}

	return false;
}
}
