package syntaxAnalyzer;

public class ForInit{

public static Object eval(Object o ){

	if( 	((Boolean)StatementExpressionList.eval(o)) ){
		return true;
	}


	if( 	((Boolean)LocalVariableDeclaration.eval(o)) ){
		return true;
	}

	return false;
}
}
